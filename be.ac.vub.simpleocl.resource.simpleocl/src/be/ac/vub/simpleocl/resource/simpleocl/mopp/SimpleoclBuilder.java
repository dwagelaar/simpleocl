/*******************************************************************************
 * Copyright (c) 2011 Dennis Wagelaar, Vrije Universiteit Brussel.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Dennis Wagelaar, Vrije Universiteit Brussel
 *******************************************************************************/
package be.ac.vub.simpleocl.resource.simpleocl.mopp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.m2m.atl.common.ATLLogger;
import org.eclipse.m2m.atl.emftvm.EmftvmFactory;
import org.eclipse.m2m.atl.emftvm.ExecEnv;
import org.eclipse.m2m.atl.emftvm.Metamodel;
import org.eclipse.m2m.atl.emftvm.Model;
import org.eclipse.m2m.atl.emftvm.util.DefaultModuleResolver;
import org.eclipse.m2m.atl.emftvm.util.ModuleResolver;
import org.eclipse.m2m.atl.emftvm.util.TimingData;

import be.ac.vub.simpleocl.SimpleoclPackage;
import be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclProblem;
import be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclTextDiagnostic;
import be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemSeverity;
import be.ac.vub.simpleocl.resource.simpleocl.SimpleoclEProblemType;

/**
 * Compiles SimpleOCL files to EMFTVM
 * @author <a href="mailto:dennis.wagelaar@vub.ac.be">Dennis Wagelaar</a>
 */
public class SimpleoclBuilder implements be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclBuilder {
	
	protected static final String PBMM_URI = "http://soft.vub.ac.be/simpleocl/2011/Problem";

	protected final ResourceSet rs = new ResourceSetImpl();
	protected final Metamodel simpleoclmm = EmftvmFactory.eINSTANCE.createMetamodel();
	protected final Metamodel pbmm = EmftvmFactory.eINSTANCE.createMetamodel();
	protected final ModuleResolver mr = new DefaultModuleResolver("platform:/plugin/" + SimpleoclPlugin.PLUGIN_ID + "/transformations/", rs);

	/**
	 * Creates a new {@link SimpleoclBuilder}.
	 * @throws CoreException
	 */
	public SimpleoclBuilder() throws CoreException {
		super();
		simpleoclmm.setResource(SimpleoclPackage.eINSTANCE.eResource());
		EPackage pack = Registry.INSTANCE.getEPackage(PBMM_URI);
		if (pack != null) {
			pbmm.setResource(pack.eResource());
		} else {
			throw new CoreException(new Status(
					Status.ERROR, SimpleoclPlugin.PLUGIN_ID, 
					String.format("Cannot find Problem metamodel (%s)", PBMM_URI)));
		}
	}

	/*
	 * (non-Javadoc)
	 * @see be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclBuilder#isBuildingNeeded(org.eclipse.emf.common.util.URI)
	 */
	public boolean isBuildingNeeded(final URI uri) {
		if (uri.isPlatformResource()) {
			final IPath simpleoclPath = new Path(uri.toPlatformString(true));
			final IFile simpleoclFile = ResourcesPlugin.getWorkspace().getRoot().getFile(simpleoclPath);
			assert simpleoclFile.exists();
			final URI emftvmURI = uri.trimFileExtension().appendFileExtension("emftvm");
			final IPath emftvmPath = new Path(emftvmURI.toPlatformString(true));
			final IFile emftvmFile = ResourcesPlugin.getWorkspace().getRoot().getFile(emftvmPath);
			return !emftvmFile.exists() || emftvmFile.getLocalTimeStamp() < simpleoclFile.getLocalTimeStamp();
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see be.ac.vub.simpleocl.resource.simpleocl.ISimpleoclBuilder#build(be.ac.vub.simpleocl.resource.simpleocl.mopp.SimpleoclResource, org.eclipse.core.runtime.IProgressMonitor)
	 */
	public IStatus build(final SimpleoclResource resource, final IProgressMonitor monitor) {
		// Check for parsing errors
		if (!resource.getErrors().isEmpty()) {
			return Status.CANCEL_STATUS;
		}

		IStatus status = Status.OK_STATUS;
		final List<EObject> pbs = new ArrayList<EObject>();

		final Model simpleoclm = EmftvmFactory.eINSTANCE.createModel();
		simpleoclm.setResource(resource);

		final Resource pr = rs.createResource(URI.createFileURI("problems.xmi"));
		final Model pbm = EmftvmFactory.eINSTANCE.createModel();
		pbm.setResource(pr);
		
		final Resource pr2 = rs.createResource(URI.createFileURI("problems2.xmi"));
		final Model pbm2 = EmftvmFactory.eINSTANCE.createModel();
		pbm2.setResource(pr2);
		
		final Resource r = rs.createResource(URI.createFileURI("out.emftvm"), "org.eclipse.m2m.atl.emftvm");
		final Model emftvmm = EmftvmFactory.eINSTANCE.createModel();
		emftvmm.setResource(r);
		
		final URI riURI = resource.getURI().trimFileExtension().appendFileExtension("emftvm");
		final Resource ri = rs.createResource(riURI, "org.eclipse.m2m.atl.emftvm");
		final Model emftvmmi = EmftvmFactory.eINSTANCE.createModel();
		emftvmmi.setResource(ri);
		
		try {

			ExecEnv env = EmftvmFactory.eINSTANCE.createExecEnv();
			env.getMetaModels().put("OCL", simpleoclmm);
			env.getMetaModels().put("Problem", pbmm);
			env.getInputModels().put("IN", simpleoclm);
			env.getOutputModels().put("PBS", pbm);
			env.loadModule(mr, "SimpleOCLWFR");
			env.run(new TimingData(), null);
			
			if (getProblems(pbm, pbs) == 0) {
				env = EmftvmFactory.eINSTANCE.createExecEnv();
				env.getMetaModels().put("OCL", simpleoclmm);
				env.getMetaModels().put("Problem", pbmm);
				env.getInputModels().put("IN", simpleoclm);
				env.getOutputModels().put("OUT", emftvmm);
				env.getOutputModels().put("PBS", pbm2);
				env.loadModule(mr, "SimpleOCLtoEMFTVM");
				env.run(new TimingData(), null);
					
				if (getProblems(pbm2, pbs) == 0) {
					env = EmftvmFactory.eINSTANCE.createExecEnv();
					env.getInputModels().put("IN", emftvmm);
					env.getOutputModels().put("OUT", emftvmmi);
					env.loadModule(mr, "InlineCodeblocks");
					env.run(new TimingData(), null);
						
					ri.save(Collections.emptyMap());
					if (ri.getURI().isPlatformResource()) {
						final IPath riPath = new Path(ri.getURI().toPlatformString(true));
						final IFile riFile = ResourcesPlugin.getWorkspace().getRoot().getFile(riPath);
						riFile.setDerived(true, new SubProgressMonitor(monitor, 0));
					}
				}
			}

			final String location = resource.getURI().toString();
			for (EObject pb : pbs) {
				ISimpleoclTextDiagnostic diag = createDiagnostic(location, pb);
				SimpleoclMarkerHelper.mark(resource, diag);
			}

		} catch (CoreException e) {
			ATLLogger.log(Level.SEVERE, e.getMessage(), e);
			status = e.getStatus();
		} catch (Exception e) {
			ATLLogger.log(Level.SEVERE, e.getMessage(), e);
			status = new Status(IStatus.ERROR, SimpleoclPlugin.PLUGIN_ID, 0, e.getMessage(), e);
		} finally {
			rs.getResources().remove(pr); // unload
			rs.getResources().remove(pr2); // unload
			rs.getResources().remove(r); // unload
			rs.getResources().remove(ri); // unload
		}

		return status;
	}
	
	/**
	 * Retrieves problem elements from <code>problems</code>.
	 * @param problems the problems model
	 * @param pbElements the collection of problem elements to augment
	 * @return the number of error problems
	 */
	protected int getProblems(final Model problems, final Collection<EObject> pbElements) {
		final Collection<EObject> pbs = (Collection<EObject>) problems.allInstancesOf(
				(EClass) pbmm.findType("Problem")); //$NON-NLS-1$

		int nbErrors = 0;
		if (pbs != null) {
			for (EObject pb : pbs) {
				EStructuralFeature severityFeature = pb.eClass().getEStructuralFeature("severity"); //$NON-NLS-1$
				if (severityFeature != null && "error".equals(((EEnumLiteral)pb.eGet(severityFeature)).getName())) { //$NON-NLS-1$
					nbErrors++;
				}
			}
			pbElements.addAll(pbs);
		}

		return nbErrors;
	}
	
	/**
	 * Creates a diagnostic object for <code>pb</code>.
	 * @param pb a problem element from the problem model
	 * @return the created diagnostic object
	 */
	protected ISimpleoclTextDiagnostic createDiagnostic(final String location, final EObject pb) {
		final EStructuralFeature severityFeature = pb.eClass().getEStructuralFeature("severity");
		final EStructuralFeature descriptionFeature = pb.eClass().getEStructuralFeature("description");
		final EStructuralFeature lineFeature = pb.eClass().getEStructuralFeature("line");
		final EStructuralFeature columnFeature = pb.eClass().getEStructuralFeature("column");
		final EStructuralFeature charStartFeature = pb.eClass().getEStructuralFeature("charStart");
		final EStructuralFeature charEndFeature = pb.eClass().getEStructuralFeature("charEnd");
		
		SimpleoclEProblemSeverity severity = SimpleoclEProblemSeverity.ERROR;
		if (severityFeature != null && "warning".equals(((EEnumLiteral)pb.eGet(severityFeature)).getName())) {
			severity = SimpleoclEProblemSeverity.WARNING;
		}
		
		final String message = descriptionFeature == null ? "" : (String) pb.eGet(descriptionFeature);
		final int line = lineFeature == null ? -1 : (Integer) pb.eGet(lineFeature);
		final int column = columnFeature == null ? -1 : (Integer) pb.eGet(columnFeature);
		final int charStart = charStartFeature == null ? -1 : (Integer) pb.eGet(charStartFeature);
		final int charEnd = charEndFeature == null ? -1 : (Integer) pb.eGet(charEndFeature);

		final ISimpleoclProblem problem = new SimpleoclProblem(message, 
				SimpleoclEProblemType.BUILDER_ERROR, severity);
		return new SimpleoclTextDiagnostic(
				problem, location, line, column, charStart, charEnd);
	}

}

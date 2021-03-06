/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.simpleocl.tests;

import org.eclipselabs.simpleocl.OclUndefinedExp;
import org.eclipselabs.simpleocl.SimpleoclFactory;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Ocl Undefined Exp</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class OclUndefinedExpTest extends OclExpressionTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(OclUndefinedExpTest.class);
	}

	/**
	 * Constructs a new Ocl Undefined Exp test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclUndefinedExpTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Ocl Undefined Exp test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected OclUndefinedExp getFixture() {
		return (OclUndefinedExp)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(SimpleoclFactory.eINSTANCE.createOclUndefinedExp());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //OclUndefinedExpTest

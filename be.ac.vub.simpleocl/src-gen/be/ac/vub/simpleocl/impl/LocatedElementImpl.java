/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package be.ac.vub.simpleocl.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;

import be.ac.vub.simpleocl.LocatedElement;
import be.ac.vub.simpleocl.SimpleoclPackage;
import be.ac.vub.simpleocl.util.LocationResource;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Located Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link be.ac.vub.simpleocl.impl.LocatedElementImpl#getLine <em>Line</em>}</li>
 *   <li>{@link be.ac.vub.simpleocl.impl.LocatedElementImpl#getColumn <em>Column</em>}</li>
 *   <li>{@link be.ac.vub.simpleocl.impl.LocatedElementImpl#getCharStart <em>Char Start</em>}</li>
 *   <li>{@link be.ac.vub.simpleocl.impl.LocatedElementImpl#getCharEnd <em>Char End</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class LocatedElementImpl extends EObjectImpl implements LocatedElement {
	/**
	 * The default value of the '{@link #getLine() <em>Line</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLine()
	 * @generated
	 * @ordered
	 */
	protected static final int LINE_EDEFAULT = -1;

	/**
	 * The default value of the '{@link #getColumn() <em>Column</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getColumn()
	 * @generated
	 * @ordered
	 */
	protected static final int COLUMN_EDEFAULT = -1;

	/**
	 * The default value of the '{@link #getCharStart() <em>Char Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharStart()
	 * @generated
	 * @ordered
	 */
	protected static final int CHAR_START_EDEFAULT = -1;

	/**
	 * The default value of the '{@link #getCharEnd() <em>Char End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharEnd()
	 * @generated
	 * @ordered
	 */
	protected static final int CHAR_END_EDEFAULT = -1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LocatedElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SimpleoclPackage.Literals.LOCATED_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int getLine() {
		final Resource r = eResource();
		if (r instanceof LocationResource) {
			return ((LocationResource) r).getLine(this);
		}
		return LINE_EDEFAULT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int getColumn() {
		final Resource r = eResource();
		if (r instanceof LocationResource) {
			return ((LocationResource) r).getColumn(this);
		}
		return COLUMN_EDEFAULT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int getCharStart() {
		final Resource r = eResource();
		if (r instanceof LocationResource) {
			return ((LocationResource) r).getCharStart(this);
		}
		return CHAR_START_EDEFAULT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public int getCharEnd() {
		final Resource r = eResource();
		if (r instanceof LocationResource) {
			return ((LocationResource) r).getCharEnd(this);
		}
		return CHAR_END_EDEFAULT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SimpleoclPackage.LOCATED_ELEMENT__LINE:
				return getLine();
			case SimpleoclPackage.LOCATED_ELEMENT__COLUMN:
				return getColumn();
			case SimpleoclPackage.LOCATED_ELEMENT__CHAR_START:
				return getCharStart();
			case SimpleoclPackage.LOCATED_ELEMENT__CHAR_END:
				return getCharEnd();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case SimpleoclPackage.LOCATED_ELEMENT__LINE:
				return getLine() != LINE_EDEFAULT;
			case SimpleoclPackage.LOCATED_ELEMENT__COLUMN:
				return getColumn() != COLUMN_EDEFAULT;
			case SimpleoclPackage.LOCATED_ELEMENT__CHAR_START:
				return getCharStart() != CHAR_START_EDEFAULT;
			case SimpleoclPackage.LOCATED_ELEMENT__CHAR_END:
				return getCharEnd() != CHAR_END_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //LocatedElementImpl

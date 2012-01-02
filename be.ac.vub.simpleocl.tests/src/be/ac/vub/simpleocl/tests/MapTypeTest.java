/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package be.ac.vub.simpleocl.tests;

import junit.textui.TestRunner;
import be.ac.vub.simpleocl.MapType;
import be.ac.vub.simpleocl.SimpleoclFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Map Type</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class MapTypeTest extends OclTypeTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(MapTypeTest.class);
	}

	/**
	 * Constructs a new Map Type test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MapTypeTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Map Type test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected MapType getFixture() {
		return (MapType)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(SimpleoclFactory.eINSTANCE.createMapType());
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

} //MapTypeTest

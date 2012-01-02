/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package be.ac.vub.simpleocl.tests;

import junit.textui.TestRunner;
import be.ac.vub.simpleocl.SimpleoclFactory;
import be.ac.vub.simpleocl.TupleTypeAttribute;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Tuple Type Attribute</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class TupleTypeAttributeTest extends LocatedElementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(TupleTypeAttributeTest.class);
	}

	/**
	 * Constructs a new Tuple Type Attribute test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TupleTypeAttributeTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Tuple Type Attribute test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected TupleTypeAttribute getFixture() {
		return (TupleTypeAttribute)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(SimpleoclFactory.eINSTANCE.createTupleTypeAttribute());
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

} //TupleTypeAttributeTest

/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package be.ac.vub.simpleocl.tests;

import junit.textui.TestRunner;
import be.ac.vub.simpleocl.SimpleoclFactory;
import be.ac.vub.simpleocl.TupleExp;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Tuple Exp</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class TupleExpTest extends OclExpressionTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(TupleExpTest.class);
	}

	/**
	 * Constructs a new Tuple Exp test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TupleExpTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Tuple Exp test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected TupleExp getFixture() {
		return (TupleExp)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(SimpleoclFactory.eINSTANCE.createTupleExp());
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

} //TupleExpTest

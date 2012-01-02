/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package be.ac.vub.simpleocl.tests;

import junit.textui.TestRunner;
import be.ac.vub.simpleocl.LambdaCallExp;
import be.ac.vub.simpleocl.SimpleoclFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Lambda Call Exp</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class LambdaCallExpTest extends VariableExpTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(LambdaCallExpTest.class);
	}

	/**
	 * Constructs a new Lambda Call Exp test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LambdaCallExpTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Lambda Call Exp test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected LambdaCallExp getFixture() {
		return (LambdaCallExp)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(SimpleoclFactory.eINSTANCE.createLambdaCallExp());
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

} //LambdaCallExpTest

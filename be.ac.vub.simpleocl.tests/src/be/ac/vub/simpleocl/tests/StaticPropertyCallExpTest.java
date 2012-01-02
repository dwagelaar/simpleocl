/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package be.ac.vub.simpleocl.tests;

import junit.textui.TestRunner;
import be.ac.vub.simpleocl.SimpleoclFactory;
import be.ac.vub.simpleocl.StaticPropertyCallExp;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Static Property Call Exp</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class StaticPropertyCallExpTest extends OclExpressionTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(StaticPropertyCallExpTest.class);
	}

	/**
	 * Constructs a new Static Property Call Exp test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StaticPropertyCallExpTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Static Property Call Exp test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected StaticPropertyCallExp getFixture() {
		return (StaticPropertyCallExp)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(SimpleoclFactory.eINSTANCE.createStaticPropertyCallExp());
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

} //StaticPropertyCallExpTest

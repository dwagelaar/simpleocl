/**
 */
package be.ac.vub.simpleocl.tests;

import junit.textui.TestRunner;
import be.ac.vub.simpleocl.CollectionRange;
import be.ac.vub.simpleocl.SimpleoclFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Collection Range</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class CollectionRangeTest extends CollectionPartTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(CollectionRangeTest.class);
	}

	/**
	 * Constructs a new Collection Range test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CollectionRangeTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Collection Range test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected CollectionRange getFixture() {
		return (CollectionRange)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(SimpleoclFactory.eINSTANCE.createCollectionRange());
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

} //CollectionRangeTest

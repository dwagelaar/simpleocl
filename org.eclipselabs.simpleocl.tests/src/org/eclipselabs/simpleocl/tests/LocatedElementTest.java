/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipselabs.simpleocl.tests;

import static org.mockito.Mockito.when;

import org.eclipse.emf.common.notify.impl.NotificationChainImpl;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipselabs.simpleocl.LocatedElement;
import org.eclipselabs.simpleocl.resource.simpleocl.mopp.SimpleoclLocationResource;
import org.mockito.Mockito;

import junit.framework.TestCase;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Located Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link org.eclipselabs.simpleocl.LocatedElement#getLine() <em>Line</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.LocatedElement#getColumn() <em>Column</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.LocatedElement#getCharStart() <em>Char Start</em>}</li>
 *   <li>{@link org.eclipselabs.simpleocl.LocatedElement#getCharEnd() <em>Char End</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public abstract class LocatedElementTest extends TestCase {
	
	/**
	 * The fixture for this Located Element test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LocatedElement fixture = null;

	/**
	 * Constructs a new Located Element test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LocatedElementTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Located Element test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(LocatedElement fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Located Element test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LocatedElement getFixture() {
		return fixture;
	}

	/**
	 * Tests the '{@link org.eclipselabs.simpleocl.LocatedElement#getLine() <em>Line</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipselabs.simpleocl.LocatedElement#getLine()
	 */
	public void testGetLine() {
		final LocatedElement locatedElement = getFixture();
		assertEquals(-1, locatedElement.getLine());
		
		final int line = 36;
		final SimpleoclLocationResource resourceMock = Mockito.mock(SimpleoclLocationResource.class);
		((InternalEObject)locatedElement).eSetResource(resourceMock, new NotificationChainImpl());
		when(resourceMock.getLine(locatedElement)).thenReturn(line);
		
		final int result = locatedElement.getLine();
		
		assertEquals(line, result);
	}

	/**
	 * Tests the '{@link org.eclipselabs.simpleocl.LocatedElement#getColumn() <em>Column</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipselabs.simpleocl.LocatedElement#getColumn()
	 */
	public void testGetColumn() {
		final LocatedElement locatedElement = getFixture();
		assertEquals(-1, locatedElement.getColumn());
		
		final int column = 37;
		final SimpleoclLocationResource resourceMock = Mockito.mock(SimpleoclLocationResource.class);
		((InternalEObject)locatedElement).eSetResource(resourceMock, new NotificationChainImpl());
		when(resourceMock.getColumn(locatedElement)).thenReturn(column);
		
		final int result = locatedElement.getColumn();
		
		assertEquals(column, result);
	}

	/**
	 * Tests the '{@link org.eclipselabs.simpleocl.LocatedElement#getCharStart() <em>Char Start</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipselabs.simpleocl.LocatedElement#getCharStart()
	 */
	public void testGetCharStart() {
		final LocatedElement locatedElement = getFixture();
		assertEquals(-1, locatedElement.getCharStart());
		
		final int charStart = 38;
		final SimpleoclLocationResource resourceMock = Mockito.mock(SimpleoclLocationResource.class);
		((InternalEObject)locatedElement).eSetResource(resourceMock, new NotificationChainImpl());
		when(resourceMock.getCharStart(locatedElement)).thenReturn(charStart);
		
		final int result = locatedElement.getCharStart();
		
		assertEquals(charStart, result);
	}

	/**
	 * Tests the '{@link org.eclipselabs.simpleocl.LocatedElement#getCharEnd() <em>Char End</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipselabs.simpleocl.LocatedElement#getCharEnd()
	 */
	public void testGetCharEnd() {
		final LocatedElement locatedElement = getFixture();
		assertEquals(-1, locatedElement.getCharEnd());
		
		final int charEnd = 39;
		final SimpleoclLocationResource resourceMock = Mockito.mock(SimpleoclLocationResource.class);
		((InternalEObject)locatedElement).eSetResource(resourceMock, new NotificationChainImpl());
		when(resourceMock.getCharEnd(locatedElement)).thenReturn(charEnd);
		
		final int result = locatedElement.getCharEnd();
		
		assertEquals(charEnd, result);
	}

} //LocatedElementTest

# SimpleOCL
SimpleOCL is an embeddable [OCL](http://www.omg.org/spec/OCL) implementation for inclusion in transformation languages for the EMF Transformation Virtual Machine (EMFTVM). SimpleOCL is built on top of the [Eclipse Modeling Framework (EMF)](http://www.eclipse.org/modeling/emf/) and [EMFText](http://www.emftext.org/).

Below you can find an example of SimpleOCL code:

```
module test;

metamodel UML : 'http://www.eclipse.org/uml2/3.0.0/UML';

import OCL;

static def : allClasses : Sequence(UML!Class) =
	OclAny::allInstances()->union(
	UML!Class.allInstances());

def : allModuleNames : Set(String) =
	self.modules.getKeys()->map(k | k.toString());

context UML!Class def : umlQualifiedName(sep : String) : String =
	(let parent : OclAny = self.refImmediateComposite() in
	if parent.oclIsUndefined() then
		self.name
	else
		parent.umlQualifiedName() + sep + self.name
	endif)
	+
	let slottest : String = 'test' in slottest;

context Collection(OclAny) def : map(f : Lambda(OclAny):OclAny) : Collection(OclAny) =
	self->collect(e | f(e));


context Collection(OclAny) def : join(f : Lambda(OclAny, OclAny) : OclAny) : OclAny =
	self->iterate(e; acc : OclAny = OclUndefined |
		if acc.oclIsUndefined() then
			e
		else 
			f(acc, e)
		endif
	);

context OclAny def : name() : String =
	'<unnamed>';

context String def : name() : String =
	if self.isEmpty() then
		super.name()
	else
		self
	endif;
	
context OclAny def : name : String =
	self.name();

context String def : name : String =
	super.name;

static def : map : Map(OclAny, OclAny) =
	Map{
		(1, 'one'),
		(2, 'two'),
		(3, 'three')
	};

static def : tuple : Tuple(a : String, b : String) =
	Tuple{
		a = 'one',
		b = 'two'
	};
	
static def : tupleType : TupleType(a : String, b : String) =
	Tuple{
		a = 'one',
		b = 'two'
	};
	
context Env static def : main() : Set(String) =
	(36 + 3.5).toString().debug('arithmetic test') +
	env.allModuleNames->join(x, y | x + ', ' + y).debug('module names') +
	Env::map.debug('map').toString() +
	Env::tuple.debug('tuple').toString();
```

This code defines a couple of attributes and operations. Note that we've added the Lambda type, so you can define your own iterator expressions, e.g.:

```ocl
list->map(x | x*2)
```

More examples can be found in [iterators.simpleocl](https://github.com/dwagelaar/simpleocl/blob/master/org.eclipselabs.simpleocl.tests/test-data/iterators.simpleocl).

To facilitate better integration with Java code, we've also added support for static attributes/operations. These are invoked using the `::` operator instead of the `.` operator. Finally, all attributes/operations without a defined context fall in the `Env` context, which stands for the environment. The environment has a single runtime instance, which can be accessed using the `env` keyword.

SimpleOCL does **not** support OCL's `pre`, `post`, and `inv` declarations. It is intended as a navigation language to be embedded in model transformation languages, and therefore only supports `def` declarations. That said, EMFTVM's integration with Java allows you to do much more.

## Installation
To download and install the SimpleOCL Eclipse plugin, install it from the following Eclipse update site:

<a href="http://marketplace.eclipse.org/marketplace-client-intro?mpc_install=609072" class="drag" title="Drag to your running Eclipse workspace to install SimpleOCL"><img class="img-responsive" src="https://marketplace.eclipse.org/sites/all/themes/solstice/public/images/marketplace/btn-install.png" alt="Drag to your running Eclipse workspace to install SimpleOCL" /></a> or https://raw.githubusercontent.com/dwagelaar/simpleocl/master/org.eclipselabs.simpleocl.updatesite/

## Running SimpleOCL/EMFTVM modules
Note that SimpleOCL modules don't do anything normally, unless you define a static main operation:

```ocl
static def : main() : OclAny = ...
```

EMFTVM includes a separate launch configuration dialog that looks very much like ATL's launch configuration dialog. It can be found via "Run -> Run Configurations...".

![EMFTVM launch configuration dialog](https://wiki.eclipse.org/images/d/d4/Emftvmlaunchconfiguration.png)

## Links
* [ATL/EMFTVM documentation on Eclipse.org](http://wiki.eclipse.org/ATL/EMFTVM)

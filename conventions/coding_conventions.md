# Coding Conventions
Author: Ray Rafael S. Abenido
## Project-Unique Conventions
None.

## Formatting Style
### Curly Braces
When working with blocks of code enclosed with curly braces, follow this style for consistency:
```
if (someCondition) {
    // the opening brace is on the same line as
    // the if-statement.
}
```
You could put the brace under the if-statement, but best to just follow the above so we are all consistent.
### Others
1. Keep the number of characters per line to be >= 80 characters.
2. One statement per line only.

## Comments
Comments are separate from Javadoc. Scroll down to see any conventions for Javadoc.

Block comments are indented at the same level as the surrounding code. They may be in `/* ... */` style or `// ...` style.

For multi-line `/* ... */` comments, subsequent lines must start with * aligned with the * on the previous line.

```
// this kind of
// comment is
// okay

/* This is also okay */

/*
 * This is also
 * okay
 */

/*
* But when the '*'s are not aligned like the ones here
* it gets annoying
*/

```

## Naming Conventions
As a general rule, **always keep things explicit**. The best names in a program are those that tell you what their job just by reading it.

For example, `int timeElapsedInMilliseconds` tells you immediately what job this variable is supposed to do. `int t` does not.
### Class Names
Class names are written in UpperCamelCase. For example,
> `public class EasyWorkload {. . .}` (correct)

> `public class easyworkload {. . .}` (wrong)

> `public class easy_workload {. . .}` (also wrong)

### Method Names
Methods/Functions (whatever you call them) are wrtten in lowerCamelCase. For example,
> `public void doSomething() {. . .}` (correct)

> `public void DoSomething()  {. . .}` (wrong)

### Variable Names
Variable names should be written lowerCamelCase. For example,
> `int studentAge`

The same rule above applies when naming parameters or local variable names.

If you are working with variables that are constants, use ALL_CAPS_LOCK. Words are separated by an underscore. For example,
> `final double GOLDEN_RATIO`

## Javadoc
Always include a Javadoc at the top of a class giving a high-level summary of what that class does:
```
/**
/**
 * {@code EasyWorkload} class is where the application starts and terminates. All packages
 * and modules needed to perform the various functions of this application are declared and
 * loaded here. The whole application will run and be executed on an instance of this class.
 * 
 * @author Ray Rafael S. Abenido
 * @since release
 * @version 1.0
 */

public class EasyWorkload {...}
```

Each function should have their own Javadoc right before it so others can have an idea of what that function does.
```
    /**
     * This method saves all the information in the application and then ends
     * the program using the {@code System.exit()} function.
     * 
     */

    public void terminateApp() {...}
```

Variables don't usually need Javadoc, but you can add one if you think its necessary.
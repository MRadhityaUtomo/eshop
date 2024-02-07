## Reflection 1

Coding standards learnt from this module :
- Optimized functions : abstraction of methods, for example I started off writing the edit and delete function with a built in for loop to search for Products by ID and then I separated it to make it clearer.
- Comments : I seldom wrote any comments or not at all since they already explain themselves, minimizing commentation clutter.
- Meaningful names : Clear and concise names such as `deleteProduct()` and `findById()`.
- Branching : Seperating the features via git branches and minimizing "push to base branch"(s).
- Flow : Understanding the flow of command and methods used in each feature 
`Controller` - > `service` -> `serviceImpl` -> `Repository`

Mistakes : 
- Not using exception throws
- Not using the setter provided by Lombok

How to fix :
- Read more documentation about the tools, imports, and library I am and will be working with.

## Reflection 2

1. - I feel like my code could use a lot of work in the future if I were to add more complex features, but for a starting point the bases are hopefully fine. 
    - First of all cover the basic functions and move up to more complex and edge case scenarios. There's no fix number on it because users may be unpredictable in the use of our projects. A good above average code coverage would be 80 - 85% code coverage.
    - 100% Code coverage only covers the basic functions. It can maybe cover more complex or niche scenarios but again users are unpredictable.

2.  It will be too wasteful and potentially deter from the clean code mentality, checking a quantity still counts as checking the attribute of a created product like name of product. If I were to add a new functionality test to check the amount of items then I would just combine it with the normal create product functional test, or if the scenario doesn't include the `create` function then I would make a new test in the same java class with combining any similar scenarios in the future to that test.
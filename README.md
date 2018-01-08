# JHeap

This is an example of how a binary heap can be implemented in java,
basically there are 2 different implementation:
```
the array start at 0
childs: 2i + 1 and 2i + 2
parent: floor((i − 1) ∕ 2)
```

```
the array start at 1 (to simplify the arithmetic)
childs: 2i and 2i+1
parent: floor(i ∕ 2)
```

in this implementation i have used the second type

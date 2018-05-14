# RecoverableHashMap

CS 4311 – Spring 2018 – hw4

- Use the Proxy pattern to support a persistent and recoverable HashMap, which we'll call `RecoverableHashMap`.
- RecoverableHashMap must also implement the `_Map_` interface. You will be responsible for logging the `clear()`, `put(K,V)`, `remove(Object)`, `replace(K, V)` functions so that in case of a "crash", you could "replay" these operations to restore the state of the `HashMap`.
- [Revised from original draft] You don't have the freedom to modify the Map interface, but we do want to support a **"flush()"** function which saves the current HashMap and clears the log, plus a **"recover()"** function which will load the current HashMap and replay the logged operations to recover the HashMap. To manage the RecoverableHashMap, define a class RecoveryManager. This class will also be generic in the key and value types. It will have:
   1. A constructor that takes a Map and two strings. The map is the object to wrap inside our RecoverableHashMap.
   2. The strings are the names of the file for storing the map.
   3. A getter for the (wrapped) map
   4. flush() and recover()

Grading:
  - 20% properly setting up the Proxy pattern
  - 10% passing along requests for functions that only read from the Map
  - 20% the functions that write to the Map
  - 30% RecoveryManager
  - 20% style

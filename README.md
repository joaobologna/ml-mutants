# ml-mutants

Mutants is an application to evaluate if a given DNA is from a mutant or not.

## how to use it

The api is available at https://ml-mutant.herokuapp.com and responds on the given endpoints:

* /mutant - Define if a given DNA is from a mutant or not. If the DNA has already been processed, the algorithm will not be executed and the data will be retrieved from the database.
* /stats  - A simple statics endpoint. Mutant and human count followed by the ratio.

## contracts


* / mutant - POST with body

``
["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
``

* / stats  - GET with response

``
{"mutants":40, "humans":100: "ratio":0.4}
``
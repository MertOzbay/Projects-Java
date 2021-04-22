Autocomplete
============

This package serves as a basis for an autocomplete application. A word prefix is used to generate a list of possible candidate words, from which a word can be chosen to update the candidate list for next time. Alternatively, if the desired word is not in the candidate list, it can also be used to update the candidate list. The size of the list is governed by the variable int max, specified during instantiation.
- The two classes generate candidate list according to different parameters. __*AutocompleteRecent*__ orders candidates according to recency, meaning the most recently picked word (with the given prefix) is the first element of the list. If the number of previously picked words isn't enough to fill up the list, the rest is filled up alphabetically.
- __*AutocompleteFrequency*__ orders candidates according to the number of times they we previously picked (with the given prefix). Most frequently picked words come first. If multiple words were picked an equal amount of times previously, they are ordered according to recency. If the number of previously picked words isn't enough to fill up the list, the rest is filled up alphabetically.  

I use a Trie data structure to efficiently store and travers all of the words in the given dictionary. I make use of Trie node values to keep record of previously picked words given a specific prefix. I use Breadth-First Search to traverse the structure alphabetically.

Both of the classes have two fundamental methods:

* *public* *List<String>* *getCandidates(String prefix)*: 

Given a prefix String, returns a List of word Strings according to the order specified by the class

*getCandidates("spo")* returns **\["spoach", "spock", "spode", ... \]**

* *public* *void* *pickCandidate(String prefix, String candidate)*:

*pickCandidate("spo", "spooky")*

*getCandidates("spo")* returns **\["spooky", "spoach", "spock", "spode", ... \]**

Specifies the word picked after inputting the prefix to the *getCandidates()* method. The Trie is updated to return the updated list the next time same prefixed is used.

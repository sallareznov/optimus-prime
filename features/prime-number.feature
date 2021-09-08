Feature: Asking a sequence of prime numbers up to a given number

  Scenario Outline: The user asks for the prime numbers up to number greater than 1
    When the user asks for the prime numbers up to the number '<number>'
    Then the system answers with the numbers '<primeNumbers>'
    Examples:
      | number | primeNumbers                                                        |
      | 2      | 2                                                                   |
      | 17     | 2,3,5,7,11,17                                                       |
      | 38     | 2,3,5,7,11,13,17,19,23,29,31,37                                     |
      | 59     | 2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59                      |
      | 75     | 2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73          |
      | 96     | 2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89 |

  Scenario Outline: The user asks for the prime numbers up to an invalid input
    When the user asks for the prime numbers up to the number '<number>'
    Then the system answers with an error
    Examples:
      | number       |
      | 1            |
      | 0            |
      | -1           |
      | not-a-number |





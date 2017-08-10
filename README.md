# GambleBreaker
A software library for quantifying how much money gambling will cost you

This library allows users to create simulations for playing Blackjack and derivative games, to show how much money the play will lose with varying strategies. 

The library also simulates card counting strategies, and demonstrates the potential for losing large amounts of money.

A test run of the full set of simulations is included in the root directory as "Sim Output.txt". To see examples of the library's use, or to tweak simulations, check out the unit test suite. 

Note that all simulations involve random outcomes. The unit tests are tuned to check for, roughly, outcomes within two standard deviations of tolerance. Therefore, test execution will usually result in some failures, but in aggregate at least 95% of the simulations should be marked as successful.

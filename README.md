## ATM on FSM

Simple example of Finite-State Machines with on Automated Teller Machine of some Bank.
Written on Java with Polymorphism form (states == classes)

### FSM schema

![fsm](fsm.png)

#### States

* h - session finished
* e - session finished with error
* q<sub>0</sub> - request PIN
* q<sub>1</sub> - choose option in main menu
* q<sub>2</sub> - enter sum to withdraw
* q<sub>3</sub> - issuing cash
* q<sub>4</sub> - output account cash
* q<sub>5</sub> - enter cash to replenish
* q<sub>6</sub> - replenish transaction

#### Actions

* z<sub>0</sub> - choosing invalid option
* z<sub>1</sub> - finishing session
* z<sub>2</sub> - correct PIN
* z<sub>3</sub> - choosing to withdraw cash
* z<sub>4</sub> - withdrawing cash
* z<sub>5</sub> - returning to menu
* z<sub>6</sub> - checking account
* z<sub>7</sub> - choosing to replenish cash
* z<sub>8</sub> - replenishing account
* z<sub>9</sub> - enter sum again
* z<sub>99</sub> - system error
* z<sub>100</sub> - wrong PIN

#### JSON schema

[Schema](fsm.json) can be opened via [this link](https://markusfeng.com/projects/graph/)

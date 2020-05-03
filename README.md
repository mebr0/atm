## ATM on FSM

Simple example of Finite-State Machines with on Automated Teller Machine of some Bank.
Written on Java with Polymorphism form (states == classes)

### FSM schema

![fsm](/home/mebro/Pictures/index.png)

#### States

* h - session finished
* e - session finished with error
* q<sub>0</sub> - request PIN
* q<sub>1</sub> - choose option in main menu
* q<sub>2</sub> - enter sum to withdraw
* q<sub>3</sub> - issuing cash

#### Actions

* z<sub>0</sub> - choosing invalid option
* z<sub>1</sub> - finishing session
* z<sub>2</sub> - correct PIN
* z<sub>3</sub> - choosing to withdraw cash
* z<sub>4</sub> - withdrawing cash
* z<sub>5</sub> - returning to menu
* z<sub>99</sub> - system error
* z<sub>100</sub> - wrong PIN

#### JSON schema

[Schema](fsm.json) can be opened via [this link](https://markusfeng.com/projects/graph/)

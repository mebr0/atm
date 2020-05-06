## ATM on FSM

Simple example of Finite-State Machines with on Automated Teller Machine of some Bank.
Written on Java with Polymorphism form (states == classes)

### FSM schema

![fsm](fsm.png)

#### States

##### Start
* s - request PIN

##### Finish
* h - session finished
* e - session finished with error

##### Intermediate
* q<sub>0</sub> - choose option in main menu
* q<sub>1</sub> - making transaction
* q<sub>2</sub> - enter sum to withdraw
* q<sub>3</sub> - output account cash
* q<sub>4</sub> - enter cash to replenish
* q<sub>5</sub> - enter sum and account to transfer
* q<sub>6</sub> - enter a pin to change

#### Actions

#### Pins
* p<sub>0</sub> - wrong PIN
* p<sub>1</sub> - correct PIN

#### Menu
* m<sub>0</sub> - returning to menu
* m<sub>1</sub> - choosing invalid option at menu

#### Finishing
* e<sub>0</sub> - finishing session
* e<sub>1</sub> - system error

#### Other
* z<sub>0</sub> - choosing to withdraw cash
* z<sub>1</sub> - asserting withdrawing
* z<sub>2</sub> - checking account
* z<sub>3</sub> - choosing to replenish cash
* z<sub>4</sub> - asserting replenish
* z<sub>5</sub> - enter sum again
* z<sub>6</sub> - choosing to transfer sum
* z<sub>7</sub> - asserting transfer
* z<sub>8</sub> - enter account again
* z<sub>9</sub> - choosing change pin
* z<sub>10</sub> - enter a pin again (wrong pin)

#### JSON schema

[Schema](fsm.json) can be opened via [this link](https://markusfeng.com/projects/graph/)

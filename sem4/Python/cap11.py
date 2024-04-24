class MealyStateMachine:
    def __init__(self):
        self.state = "A"
        self.transitions = {
            "A": {"scan": 0, "hike": ("B", 2)},
            "B": {"scan": 5, "hike": ("C", 4)},
            "C": {"scan": ("D", 8), "trace": 7},
            "D": {"scan": 8}
        }

    def _transition(self, action):
        if self.state in self.transitions:
            if action in self.transitions[self.state]:
                transition = self.transitions[self.state][action]
                if isinstance(transition, tuple):
                    self.state, result = transition
                    return result
                else:
                    return transition
        raise MealyError(f"Method '{action}' '{self.state}'")

    def scan(self):
        return self._transition("scan")

    def hike(self):
        return self._transition("hike")

    def trace(self):
        return self._transition("trace")


class MealyError(Exception):
    pass


def test():
    o = MealyStateMachine()

    # Test Case 1
    assert o.scan() == 0
    assert o.hike() == 2
    assert o.scan() == 5
    assert o.scan() == 5
    assert o.scan() == 5
    assert o.hike() == 4
    assert o.trace() == 7
    assert o.scan() == 8

    # Test Case 2
    o = MealyStateMachine()
    assert o.scan() == 0
    assert o.hike() == 2
    assert o.scan() == 5
    assert o.hike() == 4
    try:
        o.hike()
    except MealyError as e:
        assert str(e) == "Method 'hike' 'C'"
    assert o.trace() == 7
    assert o.scan() == 8

    # Test Case 3
    o = MealyStateMachine()
    assert o.scan() == 0
    assert o.hike() == 2
    assert o.scan() == 5
    assert o.hike() == 4
    assert o.trace() == 7
    assert o.scan() == 8
    try:
        o.hike()
    except MealyError as e:
        assert str(e) == "Method 'hike' 'D'"

    # Test Case 4 (95.7746%)
    o = MealyStateMachine()
    assert o.scan() == 0
    assert o.hike() == 2
    assert o.scan() == 5
    try:
        o.trace()
    except MealyError as e:
        assert str(e) == "Method 'trace' 'B'"


if __name__ == "__main__":
    test()

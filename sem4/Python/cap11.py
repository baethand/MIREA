
class MealyError(Exception):
    def __init__(self, msg):
        super().__init__(msg)


class Mili:
    states = {
        "A": {
            "scan": (0, "B"),
            "hike": (1, "C")
        },
        "B": {
            "hike": (2, "C"),
            "trace": (3, "E")
        },
        "C": {
            "hike": (4, "D"),
            "scan": (5, "C"),
            "trace": (6, "F")
        },
        "D": {
            "trace": (7, "E")
        },
        "E": {
            "scan": (8, "F")
        },
        "F": {
        },
    }

    def __init__(self, other=None):
        if other:
            self.current_state = other.current_state
        else:
            self.current_state = "A"

    def scan(self):
        if "scan" in self.states[self.current_state]:
            value, new_state = self.states[self.current_state]["scan"]
            self.current_state = new_state
            return value
        else:
            raise MealyError("scan")

    def hike(self):
        if "hike" in self.states[self.current_state]:
            value, new_state = self.states[self.current_state]["hike"]
            self.current_state = new_state
            return value
        else:
            raise MealyError("hike")

    def trace(self):
        if "trace" in self.states[self.current_state]:
            value, new_state = self.states[self.current_state]["trace"]
            self.current_state = new_state
            return value
        else:
            raise MealyError("trace")


def main():
    return Mili()


def test():
    mili = main()
    visited = {"A": 0, "B": 0, "C": 0, "D": 0, "E": 0, "F": 0}
    stack = [mili]

    def try_operation(mili, operation):
        new_mili = Mili(mili)
        try:
            operation(new_mili)
            stack.append(new_mili)
        except MealyError:
            pass

    while stack:
        current_mili = stack.pop()
        if visited[current_mili.current_state] == 1:
            continue
        visited[current_mili.current_state] += 1

        try_operation(current_mili, Mili.scan)
        try_operation(current_mili, Mili.hike)
        try_operation(current_mili, Mili.trace)










# class MealyStateMachine:
#     def __init__(self):
#         self.state = "A"
#         self.transitions = {
#             "A": {"scan": 0, "hike": ("B", 2)},
#             "B": {"scan": 5, "hike": ("C", 4)},
#             "C": {"scan": ("D", 8), "trace": 7},
#             "D": {"scan": 8}
#         }

#     def _transition(self, action):
#         if self.state in self.transitions:
#             if action in self.transitions[self.state]:
#                 transition = self.transitions[self.state][action]
#                 if isinstance(transition, tuple):
#                     self.state, result = transition
#                     return result
#                 else:
#                     return transition
#         raise MealyError(f"Method '{action}' '{self.state}'")

#     def scan(self):
#         return self._transition("scan")

#     def hike(self):
#         return self._transition("hike")

#     def trace(self):
#         return self._transition("trace")


# class MealyError(Exception):
#     pass


# def test():
#     o = MealyStateMachine()

#     # Test Case 1
#     assert o.scan() == 0
#     assert o.hike() == 2
#     assert o.scan() == 5
#     assert o.scan() == 5
#     assert o.scan() == 5
#     assert o.hike() == 4
#     assert o.trace() == 7
#     assert o.scan() == 8

#     # Test Case 2
#     o = MealyStateMachine()
#     assert o.scan() == 0
#     assert o.hike() == 2
#     assert o.scan() == 5
#     assert o.hike() == 4
#     try:
#         o.hike()
#     except MealyError as e:
#         assert str(e) == "Method 'hike' 'C'"
#     assert o.trace() == 7
#     assert o.scan() == 8

#     # Test Case 3
#     o = MealyStateMachine()
#     assert o.scan() == 0
#     assert o.hike() == 2
#     assert o.scan() == 5
#     assert o.hike() == 4
#     assert o.trace() == 7
#     assert o.scan() == 8
#     try:
#         o.hike()
#     except MealyError as e:
#         assert str(e) == "Method 'hike' 'D'"

#     # Test Case 4 (95.7746%)
#     o = MealyStateMachine()
#     assert o.scan() == 0
#     assert o.hike() == 2
#     assert o.scan() == 5
#     try:
#         o.trace()
#     except MealyError as e:
#         assert str(e) == "Method 'trace' 'B'"


# if __name__ == "__main__":
#     test()

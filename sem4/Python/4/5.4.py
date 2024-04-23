import matplotlib.pyplot as plt
import random

scale_x = 25
scale_y = 50


class NaryTree:
    def __init__(self, val, children=None):
        self.val = val
        self.children = children if children is not None else []
        self.x = 0
        self.y = 0

    def assign_coordinates(self, depth=0, left_bound=0):
        self.y = depth * scale_y

        if not self.children:
            self.x = left_bound * scale_x
            return self.x, left_bound + 1

        child_coordinates = []
        right_bound = left_bound
        for child in self.children:
            _, right_bound = child.assign_coordinates(depth + 1, right_bound)
            child_coordinates.append(child.x)

        self.x = sum(child_coordinates) / len(child_coordinates)

        return (self.x), right_bound

    def plot_tree(self):
        fig, ax = plt.subplots()
        ax.set_ylim(200, 0)  # Задаем пределы для оси y
        self._plot_tree(ax)
        plt.show()

    def _plot_tree(self, ax):
        for child in self.children:
            ax.plot([self.x, child.x], [self.y, child.y], 'k-')
            child._plot_tree(ax)
        ax.plot(self.x, self.y, 'ko')


def generate_random_tree(depth, max_children):
    if depth == 0:
        return NaryTree(random.randint(1, 100))

    num_children = random.randint(0, max_children)
    children = [generate_random_tree(depth - 1, max_children) for _ in range(num_children)]

    return NaryTree(random.randint(1, 100), children)


# Генерация случайного n-арного дерева
tree_root = generate_random_tree(3, 4)

# Назначение координат с использованием улучшенного алгоритма
tree_root.assign_coordinates()

# Построение дерева с использованием matplotlib
tree_root.plot_tree()

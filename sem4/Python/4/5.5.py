import matplotlib.pyplot as plt
import ast
import random

scale_x = 25
scale_y = 50


class ASTNode:
    def __init__(self, node):
        self.node = node
        self.children = [ASTNode(child) for child in ast.iter_child_nodes(node)]
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
        ax.text(self.x, self.y, type(self.node).__name__, fontsize=8, ha='center')


# Пример использования с улучшенным классом дерева
code = """
def factorial(n):
    if n == 0:
        return 1
    else:
        return n * factorial(n-1)
"""

tree = ast.parse(code)
root = ASTNode(tree)

# Назначение координат с использованием улучшенного алгоритма
root.assign_coordinates()

# Построение дерева с использованием matplotlib
root.plot_tree()

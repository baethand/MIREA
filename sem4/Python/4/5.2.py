import matplotlib.pyplot as plt

scale_x = 25
scale_y = 50


class Tree:
    def __init__(self, val, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
        self.x = 0
        self.y = 0

    def assign_coordinates(self, depth=0, x_counter=[1]):
        self.y = depth * scale_y
        if self.left:
            self.left.assign_coordinates(depth + 1, x_counter)

        # Назначение координаты x и увеличение счетчика.
        self.x = x_counter[0] * scale_x
        x_counter[0] += 1

        if self.right:
            self.right.assign_coordinates(depth + 1, x_counter)

    def plot_tree(self):
        fig, ax = plt.subplots()
        ax.set_ylim(200, 0)  # Задаем пределы для оси y
        self._plot_tree(ax)
        plt.show()

    def _plot_tree(self, ax):
        if self.left:
            ax.plot([self.x, self.left.x], [self.y, self.left.y], 'k-')
            self.left._plot_tree(ax)
        if self.right:
            ax.plot([self.x, self.right.x], [self.y, self.right.y], 'k-')
            self.right._plot_tree(ax)
        ax.plot(self.x, self.y, 'ko')


# Пример использования:
tree_2 = Tree(2, Tree(3, Tree(4), Tree(5)), Tree(6, Tree(7)))
tree_8 = Tree(8, Tree(9, Tree(10), Tree(11, Tree(12), Tree(13))), Tree(14))
tree_root = Tree(1, tree_2, tree_8)
tree_root.assign_coordinates()
tree_root.plot_tree()

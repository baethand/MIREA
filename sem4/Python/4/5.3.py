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


class ImprovedTree(Tree):
    def assign_coordinates(self, depth=0, left_bound=0):
        self.y = depth * scale_y

        if not self.left and not self.right:
            self.x = left_bound * scale_x
            return self.x, left_bound + 1

        if self.left:
            _, right_bound = self.left.assign_coordinates(depth + 1, left_bound)
        else:
            right_bound = left_bound

        self.x = (right_bound * scale_x)

        if self.right:
            _, new_right_bound = self.right.assign_coordinates(depth + 1, right_bound + 1)
        else:
            new_right_bound = right_bound

        return (self.x), new_right_bound


# Пример использования с улучшенным классом дерева
tree_2_improved = ImprovedTree(2, ImprovedTree(3, ImprovedTree(4), ImprovedTree(5)), ImprovedTree(6, ImprovedTree(7)))
tree_8_improved = ImprovedTree(8, ImprovedTree(9, ImprovedTree(10),
                                               ImprovedTree(11,
                                                            ImprovedTree(12),
                                                            ImprovedTree(13))),
                               ImprovedTree(14))
tree_root_improved = ImprovedTree(1, tree_2_improved, tree_8_improved)

# Назначение координат с использованием улучшенного алгоритма
tree_root_improved.assign_coordinates()

# Построение дерева с использованием matplotlib
tree_root_improved.plot_tree()

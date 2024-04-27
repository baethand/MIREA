import numpy as np
import matplotlib.pyplot as plt
from matplotlib.collections import LineCollection

class Node:
    def __init__(self, position):
        self.position = position
        self.parent = None
        self.children = []

    def add_child(self, node):
        self.children.append(node)
        node.parent = self

class Tree:
    def __init__(self, root_position):
        self.root = Node(root_position)

    def grow(self, attractors, influence_radius=1.0, kill_radius=0.1, growth_step=0.1, branching_factor=2):
        for _ in range(100):  # number of growth iterations
            new_attractors = []
            for attractor in attractors:
                node = self.find_closest_node(attractor)
                if np.linalg.norm(node.position - attractor) >= kill_radius:
                    new_attractors.append(attractor)
                if np.linalg.norm(node.position - attractor) < influence_radius:
                    direction = (attractor - node.position) / np.linalg.norm(attractor - node.position)
                    for _ in range(branching_factor):
                        new_position = node.position + growth_step * direction
                        new_node = Node(new_position)
                        node.add_child(new_node)
            attractors = new_attractors

    def find_closest_node(self, position):
        min_distance = np.inf
        closest_node = None
        nodes_to_check = [self.root]
        while nodes_to_check:
            node = nodes_to_check.pop(0)
            distance = np.linalg.norm(node.position - position)
            if distance < min_distance:
                min_distance = distance
                closest_node = node
            nodes_to_check.extend(node.children)
        return closest_node

    def plot(self):
        lines = []
        nodes_to_check = [self.root]
        while nodes_to_check:
            node = nodes_to_check.pop(0)
            if node.parent is not None:
                lines.append([node.parent.position, node.position])
            nodes_to_check.extend(node.children)
        lc = LineCollection(lines, color='green')
        fig, ax = plt.subplots()
        ax.add_collection(lc)
        ax.autoscale()
        ax.margins(0.1)
        plt.show()

# usage
attractors = [np.random.rand(2) for _ in range(1000)]  # random attractors
tree = Tree(np.array([0.5, 0.5]))  # tree root at the center
tree.grow(attractors, branching_factor=3)
tree.plot()
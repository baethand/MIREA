import math
from random import randint
from tkinter import Tk, Canvas, Button

CANVAS_WIDTH, CANVAS_HEIGHT = 800, 600
NODE_R = 15
C1, C2, C3, C4 = 2, 50, 20000, 0.1
DELAY = 10

class Vec:
    def __init__(self, x, y):
        self.x = x
        self.y = y

class Node:
    def __init__(self, text):
        self.text = text
        self.targets = []
        self.vec = Vec(0, 0)

    def to(self, *nodes):
        for n in nodes:
            self.targets.append(n)
            n.targets.append(self)
        return self

class Graph:
    def __init__(self):
        self.nodes = []

    def add(self, text):
        self.nodes.append(Node(text))
        return self.nodes[-1]

class GUI:
    def __init__(self, root):
        self.canvas = Canvas(root, width=CANVAS_WIDTH,
                             height=CANVAS_HEIGHT, bg="white")
        self.draw_button = Button(root, text="Draw", command=self.start_draw)
        self.canvas.pack()
        self.draw_button.pack()
        self.nodes = None

    def draw_node(self, x, y, text, r=NODE_R):
        self.canvas.create_oval(x - r, y - r, x + r, y + r, fill="MistyRose2")
        self.canvas.create_text(x, y, text=text)

    def draw_graph(self):
        for n in self.nodes:
            for t in n.targets:
                self.canvas.create_line(n.vec.x, n.vec.y, t.vec.x, t.vec.y)
        for n in self.nodes:
            self.draw_node(n.vec.x, n.vec.y, n.text)

    def start_draw(self):
        self.canvas.delete("all")
        random_layout(self.nodes)
        for _ in range(DELAY):
            force_layout(self.nodes)
        self.draw_graph()

def random_layout(nodes):
    for n in nodes:
        n.vec.x = randint(NODE_R * 4, CANVAS_WIDTH - NODE_R * 4 - 1)
        n.vec.y = randint(NODE_R * 4, CANVAS_HEIGHT - NODE_R * 4 - 1)

def f_spring(u, v):
    dx = u.vec.x - v.vec.x
    dy = u.vec.y - v.vec.y
    d = math.sqrt(dx * dx + dy * dy)
    if d == 0:
        return Vec(0, 0)
    else:
        return Vec(C1 * math.log(d / C2) * dx / d, C1 * math.log(d / C2) * dy / d)

def f_ball(u, v):
    dx = u.vec.x - v.vec.x
    dy = u.vec.y - v.vec.y
    d = math.sqrt(dx * dx + dy * dy)
    if d == 0:
        return Vec(0, 0)
    else:
        return Vec(-C3 / (d * d) * dx / d, -C3 / (d * d) * dy / d)

def force_layout(nodes):
    forces = {n: Vec(0, 0) for n in nodes}
    for u in nodes:
        for v in nodes:
            if u is not v:
                if v in u.targets:
                    f = f_spring(u, v)
                else:
                    f = f_ball(u, v)
                forces[u].x += f.x
                forces[u].y += f.y
    for n in nodes:
        n.vec.x += C4 * forces[n].x
        n.vec.y += C4 * forces[n].y

g = Graph()
n1 = g.add("1")
n2 = g.add("2")
n3 = g.add("3")
n4 = g.add("4")
n5 = g.add("5")
n6 = g.add("6")
n7 = g.add("7")
n1.to(n2, n3, n4, n5)
n2.to(n5)
n3.to(n2, n4)
n6.to(n4, n1, n7)
n7.to(n5, n1)

root = Tk()
w = GUI(root)
w.nodes = g.nodes
root.mainloop()

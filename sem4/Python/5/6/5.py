import matplotlib.pyplot as plt
import networkx as nx

def create_graph():
    graph = {
        'n0': ['n1'],
        'n1': ['n0', 'n2'],
        'n2': ['n1', 'n3'],
        'n3': ['n2', 'n4'],
        'n4': ['n3', 'n5'],
        'n5': ['n4']
    }
    return graph

graph = create_graph()

G = nx.DiGraph()  # Используем DiGraph для направленного графа

for node, neighbors in graph.items():
    G.add_node(node)
    for neighbor in neighbors:
        G.add_edge(node, neighbor)

pos = nx.spring_layout(G)

plt.figure(figsize=(8, 6))
nx.draw(G, pos, with_labels=True, node_color='skyblue', node_size=3000, edge_color='gray', arrows=True)

# Highlighting the start node in blue
start_node = 'n0'
nx.draw_networkx_nodes(G, pos, nodelist=[start_node], node_color='blue', node_size=3000)

# Highlighting the end nodes in green
end_nodes = ['n5']
nx.draw_networkx_nodes(G, pos, nodelist=end_nodes, node_color='green', node_size=3000)

# Highlighting the nodes with bidirectional edges in red
bidirectional_nodes = ['n1', 'n3', 'n4']
nx.draw_networkx_nodes(G, pos, nodelist=bidirectional_nodes, node_color='red', node_size=3000)

plt.show()
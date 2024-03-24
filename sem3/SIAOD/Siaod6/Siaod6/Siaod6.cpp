#include <iostream>
#include <list>
#include <queue>
#include <vector>

struct Edge {
    int destination;
    int weight;

    Edge(int dest, int w) : destination(dest), weight(w) {}
};

class Graph {
private:
    int vertices;
    bool isDirected;
    std::vector<std::list<Edge>> adjacencyList;

public:
    Graph(int v, bool directed = false) : vertices(v), isDirected(directed), adjacencyList(v) {}

    void addEdge(int u, int v, int weight) {
        if (u >= 1 && u <= vertices && v >= 1 && v <= vertices) {
            adjacencyList[u - 1].push_back(Edge(v, weight));
            if (!isDirected) {
                adjacencyList[v - 1].push_back(Edge(u, weight));
            }
        }
        else {
            std::cout << "Неверные индексы вершин!" << std::endl;
        }
    }

    void inputGraph() {
        int edges;
        std::cout << "Введите количество ребер: ";
        std::cin >> edges;

        for (int i = 0; i < edges; ++i) {
            int u, v, weight;
            std::cout << "Введите вершины u, v и вес: ";
            std::cin >> u >> v >> weight;
            addEdge(u, v, weight);
        }
    }

    void printChains() {
        for (int i = 1; i <= vertices; ++i) {
            std::cout << "Цепи, начинающиеся из вершины " << i << ": ";
            BFS(i);
            std::cout << std::endl;
        }
    }

    void BFS(int start) {
        if (start < 1 || start > vertices) {
            std::cout << "Неверная начальная вершина!" << std::endl;
            return;
        }

        std::vector<bool> visited(vertices, false);
        std::vector<int> distance(vertices, INT_MAX);
        std::queue<int> queue;

        queue.push(start);
        visited[start - 1] = true;
        distance[start - 1] = 0;

        while (!queue.empty()) {
            int current = queue.front();
            queue.pop();

            std::cout << current << " ";

            for (const Edge& edge : adjacencyList[current - 1]) {
                if (!visited[edge.destination - 1]) {
                    visited[edge.destination - 1] = true;
                    distance[edge.destination - 1] = distance[current - 1] + edge.weight;
                    queue.push(edge.destination);
                }
            }
        }

        std::cout << "\nВесы ребер от вершины " << start << ": ";
        for (int i = 0; i < vertices; ++i) {
            if (distance[i] == INT_MAX) {
                std::cout << "-1 ";
            }
            else {
                std::cout << distance[i] << " ";
            }
        }
        std::cout << std::endl;
    }

    void naturalMerge() {
        for (int i = 1; i <= vertices; ++i) {
            std::cout << "Кратчайшие пути из вершины " << i << ":" << std::endl;
            BFS(i);
            std::cout << std::endl;
        }
    }

    void printResultingTree() {
        std::cout << "\nВывод дерева:\n";

        for (int i = 1; i <= vertices; ++i) {
            std::cout << i << ": ";
            for (const Edge& edge : adjacencyList[i - 1]) {
                if (edge.destination >= 1 && edge.destination <= vertices) {
                    std::cout << "(" << edge.destination << ", " << edge.weight << ") ";
                }
                else {
                    std::cout << "(Неверный пункт назначения, " << edge.weight << ") ";
                }
            }
            std::cout << std::endl;
        }
    }
};

int main()
{
    setlocale(LC_ALL, "Rus");
    bool isDirected;
    std::cout << "Направленный ли граф? (Введите 1 если направленный, 0 если двунаправленный): ";
    std::cin >> isDirected;
    
    int vertices;
    std::cout << "Введите количество вершин: ";
    std::cin >> vertices;

    Graph graph(vertices, isDirected);

    graph.inputGraph();

    std::cout << "Введите количество ребер: " << std::endl;
    graph.printChains();

    std::cout << "\nКратчайшие пути с использованием естественного слияния:" << std::endl;
    graph.naturalMerge();

    graph.printResultingTree();

    return 0;
}

import com.example.klivvrandroidtask.data.model.City

data class TrieNode(
    val children: MutableMap<Char, TrieNode> = mutableMapOf(),
    val cities: MutableList<City> = mutableListOf()
)

class Trie {
    private val root = TrieNode()

    fun insert(city: City) {
        var current = root
        for (char in city.name.toLowerCase()) {
            current = current.children.getOrPut(char) { TrieNode() }
        }
        current.cities.add(city)
    }

    fun search(prefix: String): List<City> {
        var current = root
        for (char in prefix.toLowerCase()) {
            current = current.children[char] ?: return emptyList()
        }
        return collectCities(current)
    }

    private fun collectCities(node: TrieNode): List<City> {
        val result = mutableListOf<City>()
        result.addAll(node.cities)
        for (child in node.children.values) {
            result.addAll(collectCities(child))
        }
        return result
    }
}

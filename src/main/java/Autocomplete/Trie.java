package Autocomplete;

public class Trie<T> {
    private final TrieNode<T> root;

    public Trie(){
        root = new TrieNode<T>(null, (char)0);
    }

    public TrieNode<T> getRoot(){
        return root;
    }

    public TrieNode<T> find(String key){
        TrieNode<T> node = root;

        for(char c : key.toCharArray()){
            node = node.getChild(c);
            if (node == null) return null;
        }

        return node;
    }


    public T get(String key){
        TrieNode<T> node = find(key);

        return (node != null && node.isEndState()) ? node.getValue() : null;
    }

    public boolean contains (String key){
        return get(key) != null;
    }

    public T put (String key, T value){
        TrieNode<T> node = root;

        for(char c: key.toCharArray()){
            node = node.addChild(c);
        }

        node.setEndState(true);
        return node.setValue(value);
    }

    public boolean remove(String key){
        TrieNode<T> node = find(key);

        if (node == null || node.isEndState()){
            return false;
        }

        if (node.hasChildren()){
            node.setEndState(false);
            return true;
        }

        TrieNode<T> parent = node.getParent();

        while (parent != null){
           parent.removeChild(node.getKey());

           if (parent.hasChildren() || parent.isEndState())
               break;
           else {
               node = parent;
               parent = parent.getParent();
           }

        }

        return true;


    }



}

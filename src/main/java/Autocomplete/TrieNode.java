package Autocomplete;

import java.util.HashMap;
import java.util.Map;

public class TrieNode<T>  {
    private final Map<Character, TrieNode<T>> children;
    private TrieNode<T> parent;
    private T value;
    private boolean endState;
    private char key;

    public TrieNode(TrieNode<T> parent, char key){
        setKey(key);
        setValue(null);
        children = new HashMap<>();
        setEndState(false);
        setParent(parent);
    }

    public TrieNode<T> getParent(){
        return parent;
    }

    public T getValue(){
        return value;
    }

    public char getKey(){
        return  key;
    }

    public Map<Character, TrieNode<T>> getChildrenMap() {
        return children;
    }

    public TrieNode<T> getChild(char key){
        return children.get(key);
    }

    public void setParent(TrieNode<T> parent){
        this.parent = parent;
    }

    public void setKey(char key){
        this.key = key;
    }

    public T setValue(T value){
        T tmp = this.value;
        this.value = value;
        return tmp;
    }

    public void setEndState(boolean endState){
        this.endState = endState;
    }

    public TrieNode<T> addChild(char key){
        TrieNode<T> child = children.get(key);

        if(child == null){
            child = new TrieNode<T>(this, key);
            children.put(key, child);
        }
        return child;
    }

    public TrieNode<T> removeChild(char key){
        return children.remove(key);
    }

    public boolean isEndState() {
        return endState;
    }

    public boolean hasValue(){
        return this.value != null;
    }

    public boolean hasValue(T value){
        return this.value == value;
    }

    public boolean hasChildren(){
        return !children.isEmpty();
    }

    public boolean hasChild(char key){
        return children.containsKey(key);
    }








}

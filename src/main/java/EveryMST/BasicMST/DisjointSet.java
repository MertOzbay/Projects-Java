package EveryMST.BasicMST;

import java.util.Arrays;

public class DisjointSet {
    private final int [] subsets;

    public DisjointSet(int size){
        subsets = new int[size];
        Arrays.fill(subsets, -1);
    }

    public DisjointSet(DisjointSet set){
        subsets = Arrays.copyOf(set.subsets, set.subsets.length);
    }

    public int find (int id){
        return (subsets[id] < 0) ? id : find(subsets[id]);
    }

    public int findEfficient (int id){
        return (subsets[id] < 0) ? id : (subsets[id] = find(subsets[id]));
    }

    public boolean inSameSet(int key1, int key2){
        return find(key1) == find(key2);
    }

    public int union(int key1, int key2){
        int firstRoot = find(key1);
        int secondRoot = find(key2);
        if (firstRoot == secondRoot) return firstRoot;

        if (subsets[firstRoot] < subsets[secondRoot]){
            subsets[firstRoot] += subsets[secondRoot];
            subsets[secondRoot] = firstRoot;
            return firstRoot;
        } else {
            subsets[secondRoot] += subsets[firstRoot];
            subsets[firstRoot] = secondRoot;
            return secondRoot;
        }
    }
}

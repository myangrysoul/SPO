package MyHashSet;

import MyLList.MyLList;

import java.util.HashMap;

public class MyHashSet {

    private int size = 1 << 4;

    private MyLList table[];

    public MyHashSet() {
        table = new MyLList[size];
    }


   private static int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private static int indexFor(int h, int length) {
        return h & (length - 1);
    }

    private void addTable(int index,Object object){
        for(int i=0;i<table[index].size();i++){
            int hash=hash(table[index].get(i).hashCode());
            if(hash==hash(object.hashCode())&&table[index].contains(object)){
                table[index].replace(object);
                return;
            }
        }
        table[index].add(object);

        }

    public void add(Object object) {
        if (object == null)
            throw new NullPointerException("daldja");
        else {
            int index = indexFor(hash(object.hashCode()), size);

            if (table[index] != null) {
                addTable(index, object);
            } else {
                table[index] = new MyLList();
                table[index].add(object);
            }
        }
    }

    public void remove(Object object) {
        int index = indexFor(hash(object.hashCode()), size);
        if (table[index] != null) {
            for (int i = 0; i < table[index].size(); i++) {
                int hash = hash(table[index].get(i).hashCode());
                if (hash == hash(object.hashCode()) && table[index].contains(object)) {
                    table[index].remove(object);
                    return;
                }
            }
            throw new NullPointerException("Object is not found");

        }
    }

    public boolean contains(Object object) {
        int index = indexFor(hash(object.hashCode()), size);
        if (table[index] != null) {
            int hash = hash(table[index].hashCode());
            return table[index].contains(object);
        } else
            return false;
    }
}

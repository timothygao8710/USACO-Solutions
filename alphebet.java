
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class alphebet {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        char[] cs = br.readLine().toCharArray();
        int all = 0;
        for(char c : cs){
            if((all&(1<<(c-'a'))) != 0) continue;
            all += (1 << (c-'a'));
        }
        
        ArrayList<node> nodes = generate(all);
        int ret = Integer.MAX_VALUE;
        for(node n : nodes){
            ret = Math.min(ret, min(cs, n));
        }
        
        pw.println(ret);
        pw.close();
        br.close();
    }
    
    static int min(char[] s, node start){
        Map<Character, Integer> idx = new HashMap();
        node curr = start;
        
        curr = start;
        int t = 0;
        
        while(curr != null){
            idx.put(curr.c, t);
            t++;
            curr = curr.next;
        }
        
        int ret = 0;
        t = 0;
        for(char c : s){
            if(idx.get(c) <= t){
                ret++;
            }
            t = idx.get(c);
        }
        
        return ret;
    }
    
    
    static ArrayList<node> generate(int all){
        ArrayList<node> ret = new ArrayList();
        if(all == 0){
            ret.add(null);
            return ret;
        }
        
        for(int i = 0; i<26; i++){
            if(((1<<i)&(all)) != 0){
                ArrayList<node> next = generate(all - (1<<i));
                
                for(node n : next){
                    node temp = new node((char)(i + 'a'));
                    temp.next = n;
                    ret.add(temp);
                }
            }
        }
        return ret;
    }
    
    static class node{
        public node next;
        public char c;
        
        public node(char a){
            c = a;
        }
    }
}

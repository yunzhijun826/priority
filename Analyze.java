import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Analyze {
    public static void main(String[] args) {
        Stack zhan=new Stack();
        String input="";
        zhan.push('#');
        Priority p=new Priority();
        //Scanner sc=new Scanner(System.in);
        //String s=sc.nextLine();
        String s="i+i\n";
        //input="123";
        int len=s.length();
        input=s.substring(0,len-1);
        input=input+'#';

        char c_in=input.charAt(0);
        int i=0;
        int flag=0;
        //System.out.println(input);

//c_in!='#'||!(zhan.peek().equals('N'))||zhan.size()!=2||!(zhan.get(0).equals('#'))
        while(true){
            c_in=input.charAt(i);
            if(c_in=='#'&&zhan.size()==2&&zhan.peek().equals('N')&&zhan.get(0).equals('#')){
                break;
            }
            if(c_in!='+'&&c_in!='*'&&c_in!='('&&c_in!=')'&&c_in!='i'&&c_in!='#'){
                System.out.println('E');
                break;
            }
            char top1,top2;
            top1='\0';
            top2='\0';
            if(zhan.peek().equals('N')){
                top1=(char)zhan.pop();
                top2=(char)zhan.peek();
                flag=1;
            }
            else{
                top2=(char)zhan.peek();
            }
            int youxianji=p.priority_matrix[p.pri_map.get(top2)][p.pri_map.get(c_in)];
            if(youxianji==-1){
                System.out.println('E');
                break;
            }
            else if(youxianji==0||youxianji==2){
                if(flag==1){
                    zhan.push(top1);
                    flag=0;
                }
                zhan.push(c_in);
                i++;
                //System.out.println('I'+c_in);
                System.out.print('I');
                System.out.println(c_in);
                continue;
            }
            else{
                if(top2=='i'){
                    zhan.pop();
                    zhan.push('N');
                    System.out.println('R');
                    continue;
                }
                else if(top2=='+'){
                    zhan.pop();
                    char top3=(char)zhan.peek();
                    if(top1=='N'&&top3=='N'){
                        System.out.println('R');
                        continue;
                    }
                    else{
                        System.out.println("RE");
                        break;
                    }
                }
                else if(top2=='*'){
                    zhan.pop();
                    char top3=(char)zhan.peek();
                    if(top1=='N'&&top3=='N'){
                        System.out.println('R');
                        continue;
                    }
                    else{
                        System.out.println("RE");
                        break;
                    }
                }
                else if(top2=='('){
                    System.out.println("E");
                    break;
                }
                else if(top2==')'){
                    zhan.pop();
                    char top3=(char)zhan.pop();
                    char top4=(char)zhan.pop();
                    if(top4=='('&&top3=='N'){
                        zhan.push('N');
                        System.out.println('R');
                        continue;
                    }
                    else{
                        System.out.println("RE");
                        break;
                    }
                }
                else{
                    System.out.println("RE");
                    break;
                }
            }
        }
    }
}
class Priority{
    int [][] priority_matrix={
            {1,0,0,0,1,1},
            {1,1,0,0,1,1},
            {1,1,-1,-1,1,1},
            {0,0,0,0,2,1},
            {1,1,-1,-1,1,1},
            {0,0,0,0,0,2}
    };
    Map<Character,Integer> pri_map;
    Priority(){
        pri_map=new HashMap<>();
        pri_map.put('+',0);
        pri_map.put('*',1);
        pri_map.put('i',2);
        pri_map.put('(',3);
        pri_map.put(')',4);
        pri_map.put('#',5);
    }
}
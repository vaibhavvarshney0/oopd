import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Student implements Comparator
{
    int MAX=999;
    int insert_position=0;
    int size=0;

    Detail student_heap[]=new Detail[MAX];

    void insert(String fname, String lname, String courses[])
    {
        Detail d=new Detail(fname, lname, courses);
        student_heap[insert_position]=d;
        maintain(insert_position);
        insert_position++;
        size++;
    }

    void maintain(int position)
    {
        if(position==0)
            return;

        else
        {
            if(compare(student_heap[position], student_heap[position/2])<0)
            {
                swap(position, position/2);
                maintain(position/2);
            }
            else return;
        }
    }

    void swap(int position1, int position2)
    {
        Detail d=student_heap[position1];
        student_heap[position1]=student_heap[position2];
        student_heap[position2]=d;
    }

    void heapify(int position)
    {
        int left=2*position+1;
        int right=position*2+2;
        int smallest=position;

        if(left<size && (compare(student_heap[left], student_heap[smallest])<0))
        {
            smallest=left;
        }

        if(right<size && (compare(student_heap[right], student_heap[smallest])<0))
        {
            smallest=right;
        }

        if(smallest!=position)
        {
            swap(position, smallest);
            heapify(smallest);
        }
    }

    public int compare(Object o1, Object o2)
    {
        Detail d1=(Detail)o1;
        Detail d2=(Detail)o2;

        if((d1.fname).toLowerCase().compareTo(d2.fname.toLowerCase())<0)
        {
            return -1;
        }
        else if((d1.fname).toLowerCase().compareTo(d2.fname.toLowerCase())>0)
        {
            return 1;
        }
        else
        {
            if((d1.lname).toLowerCase().compareTo(d2.lname.toLowerCase())<0)
            {
                return -1;
            }
            else if((d1.lname).toLowerCase().compareTo(d2.lname.toLowerCase())>0)
            {
                return 1;
            }
        }
        return 0;
    }


    void maximum()
    {
        Detail d=student_heap[0];

        if(size==0)
        {
            System.out.println("NO element present!!");
            return;
        }

        System.out.print("Top element is ---> ");
        System.out.println(d.fname+" "+d.lname);
    }

    void extract_max()
    {
        if(size==0)
        {
            System.out.println("No element present!!");
            return;
        }

        Detail d=student_heap[0];
        System.out.println("Extracted element is : "+ d.fname+" "+ d.lname);
        student_heap[0]=student_heap[--insert_position];
        student_heap[insert_position]=null;
        size--;

        heapify(0);

        return;
    }

    void show()
    {
        if(size==0)
        {
            System.out.println("No element present!!");
            return;
        }
        System.out.println("-------------STUDENT DETAILS-----------");
        for(int i=0;i<insert_position;i++)
        {
            Detail d=student_heap[i];
            System.out.print(d.fname+" "+d.lname+"  |  ");
            System.out.println("courses : "+ Arrays.toString(d.course));
            System.out.println("--------------------------------------");
        }
    }

    int search(String name)
    {
        for(int i=0;i<insert_position;i++)
        {
            String name2=student_heap[i].fname+student_heap[i].lname;
            if(name.compareTo(name2)==0)
                return i;
        }

        return -1;
    }

    void delete(String fname, String lname)
    {
        String name=fname+lname;
        int pos=search(name);

        if(pos==-1)
        {
            System.out.println("no such detail exist!!");
            return;
        }
        student_heap[pos]=student_heap[--insert_position];
        size--;

        heapify(pos);
        System.out.println();
        System.out.println("deleted!!");
        System.out.println();
    }
}


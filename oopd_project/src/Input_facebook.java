import com.restfb.*;
import com.restfb.types.Comment;
import com.restfb.types.FacebookType;
import com.restfb.types.Page;
import com.restfb.types.Post;

import java.util.Date;
import java.util.List;

public class Input_facebook
{
    public static void main(String[] args)
    {
        String access_token = "EAACEdEose0cBAPGgy8w2BfYMYSPGZBknCu3k6xhhAghZCQl7hE6HbE8smjiTKDDC9QPNulKREWsgbZAaGqAcZAcgolcoxMhnGUQ3v3XFguqZAuw7KJxDVn2jAS8dy6xyvQcG08DrvgtngfAjrwMOqCD81g2IVqtqZC0IZBeD0JODcwq6pPTkFJwoWv9gm366gBzrgZBNvZANldAZDZD";
        FacebookClient fb_client = new DefaultFacebookClient(access_token, Version.LATEST);

        //get all post from the page
        Page page = fb_client.fetchObject("145038352809268", Page.class);
        Connection<Post> postFeed = fb_client.fetchConnection(page.getId() + "/feed", Post.class);

        Admin admin = new Admin();
        Student student = new Student();
        Employee employee = new Employee();

        //loop over all posts
        for(List<Post> all_posts : postFeed)
        {
            for (Post post : all_posts)
            {
                Post p1 = fb_client.fetchObject(post.getId(), Post.class, Parameter.with("fields", "from"));
                String message = post.getMessage();
                System.out.println("------------------------------------");
//                System.out.println(message);;
                String[] input_token = message.split("\\s+");
                String post_id = post.getId();
                String post_sender_id = (p1.getFrom().getId());
                String post_sender_name = p1.getFrom().getName();

                //System.out.println("enter");
                if(input_token[0].toLowerCase().compareTo("register") == 0 && input_token[1].compareTo("student") == 0)
                {
                    int r=student.register(post_sender_id, post_sender_name, post_id, input_token);
                    if(r==1)
                    {
                        FacebookType response=fb_client.publish(post.getId() + "/comments", Comment.class,Parameter.with("message", "Student registered"));
//                        System.out.println("fb.com/"+response.getId());
                    }
                }
                else if(input_token[0].toLowerCase().compareTo("register") == 0 && input_token[1].compareTo("employee") == 0)
                {
                    int r=employee.register(post_sender_id, post_sender_name, post_id, input_token);
                    if(r==1)
                    {
                        FacebookType response=fb_client.publish(post.getId() + "/comments", Comment.class,Parameter.with("message", "Employee registered"));
//                        System.out.println("fb.com/"+response.getId());
                    }
                }

                else if(input_token[0].toLowerCase().compareTo("request") == 0)
                {
                    //System.out.println("post_sender_id"+post_sender_id);
                    int r=student.fileRequest(post_sender_id, post_sender_name, post_id, input_token);
                    if(r==1)
                    {	FacebookType response=fb_client.publish(post.getId() + "/comments", Comment.class,Parameter.with("message", "Your work will be completed within 24 hrs!"));
                    }
                    else if(r==2)
                    {	FacebookType response=fb_client.publish(post.getId() + "/comments", Comment.class,Parameter.with("message", "No employee available right now"));
//                        System.out.println("No employee available right now!");
                    }
                    else
                    {
                        System.out.println("return is 0");
                    }

                    //check status
                    Connection<Comment> comments = fb_client.fetchConnection(post.getId() + "/comments", Comment.class);
                    String complaint_id=post.getId();
                    String work=input_token[1];

                    for (List<Comment> comment_list:comments)
                        for (Comment c:comment_list)
                        {
                            String comment_id = c.getId();
                            String comment_sender_id = c.getFrom().getId();
                            String comment_sender_name = c.getFrom().getName();
                            String comment_msg=c.getMessage();

                            String[] comment_token = comment_msg.split("\\s+");
//                            System.out.println("complaint_id : "+complaint_id);
//                            System.out.println("comment_sender_id : "+comment_sender_id);
//                            System.out.println("comment_msg : "+comment_msg);
//                            System.out.println(" comment first word : " + comment_token[0]);

                            if(comment_token[0].toLowerCase().compareTo("status") == 0)
                            {
                                System.out.println("before function call");

                                Date date1=c.getCreatedTime();
                                System.out.println("dt1"+date1);
                                Date date2=post.getCreatedTime();
                                System.out.println("dt2"+date2);
                                long diff=(date2.getTime()-date1.getTime())/1000;

                                int r2 = employee.updateRequestStatus(comment_sender_id, comment_sender_name, complaint_id, comment_token, diff);
                                admin.report();

                                if(r2==1)
                                {
                                    FacebookType response=fb_client.publish(post.getId() + "/comments", Comment.class,Parameter.with("message", "Requested feedback"));
//                                    System.out.println("fb.com/"+response.getId());
                                }
                                else if(r2==0)
                                {
                                }

                                else if(r2 ==-1)
                                {
                                    FacebookType response=fb_client.publish(post.getId() + "/comments", Comment.class,Parameter.with("message", "new employee will be allocated next time!!!"));
                                }
                            }

                            else if(comment_token[0].toLowerCase().compareTo("feedback") == 0)
                            {

                                if(employee.saveFeedback(comment_sender_id, comment_sender_name, complaint_id, comment_msg) == 1)
                                {
                                    FacebookType response=fb_client.publish(post.getId() + "/comments", Comment.class,Parameter.with("message", "Thank you for your wonderful feedback!"));
                                }
                            }
                        }

                }


            }
        }
        //}
        //In every run check if assigned task and completed tasks >=10 for any employee
//        db.fireEmployee();

        admin.viewReport();


    }
}

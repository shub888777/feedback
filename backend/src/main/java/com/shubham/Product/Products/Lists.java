package com.shubham.Product.Products;

public class Lists {

    public static void main(String[] args) {
        String str="check the manual that corresponds to your MariaDB server version for the right syntax to use near ";

        String rev="";
        String arr[]=str.split(" ");

        for(int i=0;i<arr.length;i++){
            StringBuffer sb=new StringBuffer();

            sb.append(arr[i]);
            sb.reverse();
            rev=rev+sb+" ";
        }

        System.out.println(rev);
    }

}

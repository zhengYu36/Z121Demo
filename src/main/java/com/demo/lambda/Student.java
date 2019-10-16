package com.demo.lambda;

/**
 * <ul>
 * <li>文件包名 : com.demo.lambda</li>
 * <li>创建时间 : 2019/3/28 9:54</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：可以在实体中来进行比较，当然也可以通过 Collections工具类来实现
 *
 * @author zhengyu
 */
public class Student{

    public Student(String name, Double score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if(obj instanceof Student){
            Student user =(Student)obj;
            if(user.name.equals(this.name)) return true;
//			if(user.id = this.id) return true; // 只比较id
            // 比较id和username 一致时才返回true 之后再去比较 hashCode
           // if(user.id == this.id && user.username.equals(this.username)) return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    private String name;
    private Double score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    /*@Override
    public int compareTo(Object o) {
        Student s = (Student) o;
        if(this.getScore()>s.getScore()){
            return 0;
        }

        if(this.getScore().equals(s.getScore())){
            if(this.getName().compareTo(((Student) o).getName()) > 0){
                return 0;
            }
        }

        return 0;
    }*/
}

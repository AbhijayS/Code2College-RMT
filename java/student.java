import java.util.*;
import java.io.*;

class student{
  private static final int NUM_QUESTIONS = 7;
  private String first;
  private String last;
  private static char riskFactor;
  private static char changeDegree;
  private String school;
  private static HashMap<Integer, ArrayList<String>> qa = new HashMap<>();

  public student(String first, String last, String school){
    this.first = first;
    this.last = last;
    this.school = school;
  }

  public String toString(){
    return first+" "+last+"\n"+"Risk Factor of Dropping Out of College: "+riskFactor+"\n"+"Risk Factor of Changing Degrees: "+changeDegree;
  }

  public static void determineRF(ArrayList<student> students, HashMap<student, ArrayList<String>> studentAnswers) {
    for(int i = 0; i < students.size(); i++) {
      int rawScore = 0;
      for(int j = 0; j < NUM_QUESTIONS; j++){
        if(j==0){
          rawScore += 2*(qa.get(j).indexOf(studentAnswers.get(students.get(i)).get(j)));
        }else if(j == 1){
          determineCD(studentAnswers.get(students.get(i)).get(j));

        }else {
          int tmp = decision(studentAnswers.get(students.get(i)).get(j));
          if(j==2){
            rawScore += 3*tmp;
          }else if(j==3){
            rawScore += 2*tmp;
          }else{
            rawScore += tmp;
          }
        }
      }
      // System.out.println(rawScore);
      setRiskFactor(getRiskFactor(rawScore));
    }
  }

  public static char getRiskFactor(int rf){
   /*rf+=qa.get(0).indexOf(missedDays);
   rf+=qa.get(2).indexOf(goToCollege);
   rf+=ynm(extraCurricular);
   rf+=ynm(friend);
   rf+=ynm(help);*/
   if(0<=rf && rf<=4){
     return 'A';
   }
   else if(5<=rf && rf<=9){
     return 'B';
   }
   else if(10<=rf && rf<=14){
     return 'C';
   }
   else if(15<=rf && rf<=19){
     return 'D';
   }
   else if(20<=rf && rf<=22){
    return 'F';
   }
   else{
     return 'X';
   }
 }

  public static void determineCD(String a){
    int q = qa.get(1).indexOf(a);
    switch(q){
      case 0:
        setChangeDegree('F');
        break;
      case 1:
        setChangeDegree('A');
        break;
      case 2:
        setChangeDegree('A');
        break;
      case 3:
        setChangeDegree('C');
        break;
      case 4:
        setChangeDegree('B');
        break;
      case 5:
        setChangeDegree('D');
        break;
      case 6:
        setChangeDegree('C');
        break;
      default:
        setChangeDegree('X');
        break;
    }
  }

  public static int decision(String a){
    switch(a){
      case "Yes": return 0;
      case "Maybe": return 1;
      case "No": return 1;
      default: return -1;
    }
  }

  public static void setChangeDegree(char degree){
    changeDegree = degree;
  }

  public static void setRiskFactor(char rf){
    riskFactor = rf;
  }

  public static void main(String[] args) throws Exception{
    System.out.println("Enter file name ...");
    Scanner scan = new Scanner(System.in);
    String fileName = scan.nextLine();
    scan = new Scanner(new File(fileName));
    qa.put(0, new ArrayList<String>(Arrays.asList("None","1 or 2","3 to 5","6-10","10+")));
    qa.put(1, new ArrayList<String>(Arrays.asList("Liberal arts (ex: philosophy teacher)","Engineering and Technology (ex: software developer)","Science and/or Medicine (ex: doctor)","Skilled Trades (ex: electrician)","Business (ex: accounting)","Service (ex: customer service representative)","Law (ex: lawyer)")));
    qa.put(2, new ArrayList<String>(Arrays.asList("Yes","Maybe","No")));
    qa.put(3, new ArrayList<String>(Arrays.asList("Yes","No","Maybe")));
    qa.put(4, new ArrayList<String>(Arrays.asList("Yes","No")));
    qa.put(5, new ArrayList<String>(Arrays.asList("Yes","No")));
    qa.put(6, new ArrayList<String>(Arrays.asList("Yes","No")));

    HashMap<student, ArrayList<String>> studentAnswers = new HashMap<student, ArrayList<String>>();
    ArrayList<student> students = new ArrayList<student>();

    boolean start = true;
    while(scan.hasNext()) {
      String input[] = scan.nextLine().split(",");
      if(start != true){
        ArrayList<String> answers = new ArrayList<String>();
        for(int i = 4; i < input.length; i++){
          answers.add(input[i]);
        }
        // System.out.println(input[3]);
        student newStudent = new student(input[1], input[2], input[3]);
        // System.out.println(newStudent);
        studentAnswers.put(newStudent, answers);
        students.add(newStudent);
      }
      start = false;
    }
    determineRF(students, studentAnswers);
    for(int i = 0; i < students.size(); i++){
      System.out.println(students.get(i));
      System.out.println("");
    }
  }
}

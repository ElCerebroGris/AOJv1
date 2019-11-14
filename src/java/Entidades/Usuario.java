/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Vector;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Zamba
 */
public class Usuario implements Serializable, Comparable<Usuario> {

    private long id;
    private int role;
    private String login;
    private String password;
    private String confirm_password;
    private String first_name;
    private String last_name;
    private String email;
    private String gender;
    private String birthday;
    private String country;
    private String institution;
    private String gui_language;
    private String secure_question;
    private String secure_answer;
    
    private float pontos;
    private boolean state;
    private String last_submission;
    private int solved;//for contest too
    //Contest
    private Vector<ProblemaAd> problems;
    private boolean contestant;
    private long total_time;

    public Usuario() {
        problems = new Vector<>();
    }

    public Usuario(int role, String login, String password, String first_name, String last_name, String email,
            String gender, String brithday, String country, String institution, String gui_language,
            float pontos) {
        this.role = role;
        this.login = login;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.gender = gender;
        this.birthday = brithday;
        this.country = country;
        this.institution = institution;
        this.gui_language = gui_language;
        this.pontos = pontos;
        problems = new Vector<>();
    }

    public Usuario(long id, int role, String login, String password, String first_name, String last_name,
            String email, String gender, String brithday, String country, String institution,
            String gui_language, float pontos) {
        this.id = id;
        this.role = role;
        this.login = login;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.gender = gender;
        this.birthday = brithday;
        this.country = country;
        this.institution = institution;
        this.gui_language = gui_language;
        this.pontos = pontos;
        problems = new Vector<>();
    }

    public Usuario(long id, int role, String login, String password, String first_name, String last_name,
            float pontos, String email, String gender) {
        this.id = id;
        this.role = role;
        this.login = login;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.gender = gender;
        this.pontos = pontos;
        problems = new Vector<>();
    }

    public Usuario(long id, int role, String login, String password, String first_name, String last_name,
            String email, String gender, String country, String institution,
            float pontos, boolean state, String last_submission, int solved) {
        this.id = id;
        this.role = role;
        this.login = login;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.gender = gender;
        this.country = country;
        this.institution = institution;
        this.pontos = pontos;
        this.state = state;
        this.last_submission = last_submission;
        this.solved = solved;
        problems = new Vector<>();
    }

    public Usuario(long id, String login, String gender, String country, String institution, float pontos, String last_submission, int solved) {
        this.id = id;
        this.login = login;
        this.gender = gender;
        this.country = country;
        this.institution = institution;
        this.pontos = pontos;
        this.last_submission = last_submission;
        this.solved = solved;
        problems = new Vector<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getGui_language() {
        return gui_language;
    }

    public void setGui_language(String gui_language) {
        this.gui_language = gui_language;
    }

    public float getPontos() {
        return pontos;
    }

    public void setPontos(float pontos) {
        this.pontos = pontos;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getLast_submission() {
        return last_submission;
    }

    public void setLast_submission(String last_submission) {
        this.last_submission = last_submission;
    }

    public int getSolved() {
        return solved;
    }

    public void setSolved(int solved) {
        this.solved = solved;
    }

    public Vector<ProblemaAd> getProblems() {
        return problems;
    }

    public void setProblems(Vector<ProblemaAd> problems) {
        this.problems = problems;
    }

    public boolean isContestant() {
        return contestant;
    }

    public void setContestant(boolean contestant) {
        this.contestant = contestant;
    }

    public long getTotal_time() {
        return total_time;
    }

    public void setTotal_time(long total_time) {
        this.total_time = total_time;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }

    public String getSecure_question() {
        return secure_question;
    }

    public void setSecure_question(String secure_question) {
        this.secure_question = secure_question;
    }

    public String getSecure_answer() {
        return secure_answer;
    }

    public void setSecure_answer(String secure_answer) {
        this.secure_answer = secure_answer;
    }
    
    @Override
    public int compareTo(Usuario o) {
        int m1 = 0;
        int m2 = 0;
        int penality1 = 0;
        int penality2 = 0;
        //Comparar pelo numero de problemas resolvidos
        if (Integer.compare(o.getSolved(), solved) != 0) {
            return Integer.compare(o.getSolved(), solved);
        }
        /*
        for (int i = 0; i < problems.size() && i < o.getProblems().size(); i++) {
            
            if (problems.get(i).getPenalidade()> 0) {
                penality1 += problems.get(i).getPenalidade();
                ++m1;
            }
            if (o.getProblems().get(i).getPenalidade()> 0) {
                penality2 += o.getProblems().get(i).getPenalidade();
                ++m2;
            }
        }*/
        //Se é o numero de problemas resolvidos forem iguais
        //Comparar tempo de penalização
        if (m1 == m2) {
            if (penality1 > penality2) {
                return -1;
            }
            return 0;
        }
        if (m1 > m2) {
            return -1;
        }
        return 1;
    }

}

package jpabook.jpashop.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","username","age"})
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY) // JPA 연관관계는 지연로딩으로 EAGER로 하게되면 성능최적화하기 어렵다
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username){
        this.username = username;
    }
    public Member(String username, int age, Team team){
        this.username  = username;
        this.age = age;
        if(team != null){
            changeTeam(team);
        }
    }
    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);

    }
}

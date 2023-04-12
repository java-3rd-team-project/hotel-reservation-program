import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Member {

    String name;
    String phone;
    String email;
    Gender gender;
    int mileage;
    int history;
    List<Reservation> reservationList;


    public Member() {
    }

    public Member(String name, String phone, String email, Gender gender) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.history = 0;
        this.mileage = 0;
        this.reservationList = new ArrayList<>();
    }

    public void increaseMileage(int cost){
        double mileageRate = getMileageRate();
        this.mileage += (cost * mileageRate);
    }

    public double getMileageRate() {
        double v;
        switch (this.history / 10) {
            case 0:
                v = 0.01;
                break;
            case 1:
                v = 0.02;
                break;
            case 2:
            case 3:
            case 4:
                v = 0.05;
                break;
            default:
                v = 0.1;
        };
        return v;
    }
    public void addReservationList(Reservation reservation){
        increaseMileage((int)reservation.getCost());
        this.reservationList.add(reservation);
        setHistory(getHistory() + 1);
    }
    public void removeReservationList(Reservation reservation){
        setHistory(getHistory() - 1);
        this.reservationList.remove(reservation);
        decreaseMileage((int)reservation.getCost());
    }

    private void decreaseMileage(int cost) {
        double mileageRate = getMileageRate();
        this.mileage -= (cost * mileageRate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getHistory() {
        return history;
    }

    public void setHistory(int history) {
        this.history = history;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return phone.equals(member.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phone);
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", mileage=" + mileage +
                ", history=" + history +
                ", reservationList=" + reservationList +
                '}';
    }
}
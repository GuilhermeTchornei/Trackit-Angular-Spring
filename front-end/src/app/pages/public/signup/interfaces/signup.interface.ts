import Signin from "../../signin/interfaces/signin.interface";

export default interface Signup extends Signin {
    name: string;
    photo: string;
}
import { Week } from "../enums/week.enum";

export default interface NewHabit {
    name: string;
    days: Week[];
}
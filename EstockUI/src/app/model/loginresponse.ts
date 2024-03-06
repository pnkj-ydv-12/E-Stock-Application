import { user } from './user'; // replace './user' with the actual path
 
export interface LoginResponse {
  user: user[];
  token: string;
}
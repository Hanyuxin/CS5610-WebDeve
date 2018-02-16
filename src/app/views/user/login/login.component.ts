import {Component, OnInit, ViewChild} from '@angular/core';
import {Router} from '@angular/router';
import {NgForm} from '@angular/forms';
import {UserService} from '../../../services/user.service.client';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @ViewChild('f') loginForm: NgForm;
  username: String;
  passname: String;
  constructor(private userService: UserService, private router: Router) { }
  login() {
    this.username = this.loginForm.value.username;
    this.passname = this.loginForm.value.password;

    const user = this.userService.findUserByCredential(this.username, this.passname);
    console.log(user);
    this.router.navigate(['/user', user._id]);
  }
  register() {
    this.router.navigate(['/register']);
  }

  ngOnInit() {
  }

}

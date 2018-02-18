import { Component, OnInit } from '@angular/core';
import {User} from '../../../models/user.model.client';
import {UserService} from '../../../services/user.service.client';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: User;
  constructor(
    private userService: UserService,
    private router: Router,
    private route: ActivatedRoute) { }

  updateUser(user) {
    console.log(user);
    this.user = this.userService.updateUser(user);
  }
  logout() {
    this.router.navigate(['/login']);
  }
  ngOnInit() {
    this.route.params.subscribe(params => {
      console.log(params['_id']);
      this.user = this.userService.findUserById(params['_id']);
    });
  }

}

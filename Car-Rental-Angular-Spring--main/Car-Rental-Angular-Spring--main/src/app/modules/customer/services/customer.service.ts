import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { StorageService } from '../../../authentication/services/storage/storage.service';
 
const BASE_URL = 'http://localhost:8084/api/v1/';
const ADMIN_URL ='http://localhost:8081/api/v1/';
const CHANGE_URL ='http://localhost:8082sks/api/v1/bookings/';
 
 
@Injectable({
  providedIn: 'root'
})
export class CustomerService {
 
 
  constructor(
    private http: HttpClient
  ) { }
 
 
  // function to get all cars
  getAllCars(): Observable<any> {
    return this.http.get(BASE_URL + 'customer/bikes', {
      //headers: this.createAuthorizationHeader(),
    });
  }
 
  // function to get car by id
  getCarById(carId: number): Observable<any> {
    return this.http.get(BASE_URL + 'customer/bikes/' + carId, {
      headers: this.createAuthorizationHeader(),
    });
  }
 
    // function to get car by id
    carBooking(book: any): Observable<any> {
      return this.http.post(CHANGE_URL + 'car/book' , book, {
        // headers: this.createAuthorizationHeader(),
      });
    }
 
 
      // function to get car by id
      getBookingByUserId(): Observable<any> {
    return this.http.get(BASE_URL +'bookings/car/' + StorageService.getUserId(), {
      headers: this.createAuthorizationHeader(),
    });
  }
 
      // function to search car
      search(SearchCarDto: any): Observable<any> {
        return this.http.post(BASE_URL + 'customer/car/search', SearchCarDto, {
          headers: this.createAuthorizationHeader(),
        });
      }
 
  createAuthorizationHeader(): HttpHeaders {
    let authHeaders: HttpHeaders = new HttpHeaders();
    return authHeaders.delete(
      'Authorization',
    );
  }
 
 
}
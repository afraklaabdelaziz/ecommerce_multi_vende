import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListCommandeClientComponent } from './list-commande-client.component';

describe('ListCommandeClientComponent', () => {
  let component: ListCommandeClientComponent;
  let fixture: ComponentFixture<ListCommandeClientComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListCommandeClientComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListCommandeClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

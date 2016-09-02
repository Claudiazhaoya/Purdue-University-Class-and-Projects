		while(temp != NULL){
			if(temp->next ==node){
				temp->next=NULL;
			}
			temp = temp->next;
		}
	
	free(node->word);
	free(node);
	}
	else{
		while(temp !=NULL){
			if(temp->next== node){
				temp->next=node->next;
				temp->prev=node->prev;
			}
			temp=temp->next;
		}
		free(node->word);
		free(node);
	}


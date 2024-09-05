import NiceModal, { useModal } from '@ebay/nice-modal-react';
import { IconButton } from '@mui/material';
import { XIcon } from 'lucide-react';

import {
  BaseModal, ModalBody, ModalHeader, ModalTitle,
} from '@/components/modals/Modal.tsx';

export interface ImageModalProps {
  alt: string;
  src: string;
}

export default NiceModal.create((props: ImageModalProps) => {
  const { alt, src } = props;
  const { remove, visible } = useModal();
  return (
    <BaseModal isOpen={visible} onClose={remove}>
      <ModalHeader className="items-center justify-between">
        <ModalTitle>{alt}</ModalTitle>
        <IconButton onClick={remove}>
          <XIcon />
        </IconButton>
      </ModalHeader>
      <ModalBody className="min-h-0 min-w-0 items-center justify-center !p-0">
        <img alt={alt} className="object-scale-down" src={src} />
      </ModalBody>
    </BaseModal>
  );
});

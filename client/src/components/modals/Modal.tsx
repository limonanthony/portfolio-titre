import {
  Box, Modal, Paper, Typography,
} from '@mui/material';

import { ChildrenProp, ClassNameProp } from '@/types/prop.types.ts';
import ClassUtils from '@/utils/Class.utils.ts';

export interface BaseModalProps extends Partial<ClassNameProp>, ChildrenProp {
  isOpen: boolean;
  onClose?: () => void;
}

export interface GenericModalProps extends ChildrenProp, Partial<ClassNameProp> {}

export function ModalTitle({ children, className }: GenericModalProps) {
  return (
    <Typography className={className} fontWeight="bold" variant="h5">
      {children}
    </Typography>
  );
}

export function ModalHeader({ children, className }: GenericModalProps) {
  return <Box className={ClassUtils.concat('flex w-full px-6 py-4', className)}>{children}</Box>;
}

export function ModalBody({ children, className }: GenericModalProps) {
  return (
    <Box
      className={ClassUtils.concat(
        'flex max-h-full min-h-0 min-w-0 max-w-full flex-1 overflow-y-scroll px-6 py-4',
        className,
      )}
    >
      {children}
    </Box>
  );
}

export function ModalFooter({ children, className }: GenericModalProps) {
  return <Box className={ClassUtils.concat('flex', className)}>{children}</Box>;
}

export function BaseModal(props: BaseModalProps) {
  const {
    children, className, isOpen, onClose,
  } = props;

  return (
    <Modal onClose={onClose} open={isOpen}>
      <Paper
        className={ClassUtils.concat(
          'absolute left-1/2 top-1/2 flex max-h-[80vh] max-w-full flex-1 -translate-x-1/2 -translate-y-1/2 flex-col md:w-[80vh]',
          className,
        )}
      >
        {children}
      </Paper>
    </Modal>
  );
}
